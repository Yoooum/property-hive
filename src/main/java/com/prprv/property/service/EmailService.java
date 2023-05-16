package com.prprv.property.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
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

    public void sendActivationEmail( String email, String activationCode) {

        // 创建邮件消息
        EmailMessage message = new EmailMessage(email, activationCode);

        // 将消息发送到RabbitMQ队列中
        rabbitTemplate.convertAndSend("email.queue", message);
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
