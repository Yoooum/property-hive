package com.prprv.property.service;

import com.prprv.property.common.response.E;
import com.prprv.property.entity.sys.User;
import com.prprv.property.exception.AppException;
import com.prprv.property.repo.sys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author phj233
 * @since 2023/5/16 8:25
 */
@Service
@RequiredArgsConstructor
public class EmailService {
    private final RedisTemplate<String, String> redisTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;

    public void sendActivationEmail(String email, String activationCode) {
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new AppException(E.ACTIVATION_FAILED);
        }
        Optional<User> user = userRepository.findByEmail(email);
        user.map(u -> {
            if (u.getActivated()) {
                throw new AppException(E.USER_ACTIVATED);
            }
            return u;
        });
        String code = redisTemplate.opsForValue().get(email + ":code");
        if (code != null && !code.equals(activationCode)) {
            throw new AppException(E.CODE_EXPIRED);
        }
        try {
            // 创建邮件消息
            EmailMessage message = new EmailMessage(email, activationCode);
            // 将消息发送到RabbitMQ队列中
            rabbitTemplate.convertAndSend("email.queue", message);
        }catch (Exception e){
            throw new AppException(E.EMAIL_SEND_FAILED);
        }
    }

    public String generateActivationCode(String email) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        // 将激活代码存储到Redis中
        redisTemplate.opsForValue().set(email+":code", stringBuilder.toString(), 5 * 60, TimeUnit.SECONDS);
        return stringBuilder.toString();
    }

    public String getActivationCode(String email) {
        try {
            String code = redisTemplate.opsForValue().get(email + ":code");
            if (!StringUtils.hasText(code)) {
                code = generateActivationCode(email);
            }
            return code;
        }catch (Exception e){
            return  e.getMessage();
        }

    }
}
