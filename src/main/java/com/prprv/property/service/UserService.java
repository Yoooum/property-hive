package com.prprv.property.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prprv.property.common.response.E;
import com.prprv.property.entity.sys.User;
import com.prprv.property.entity.value.Register;
import com.prprv.property.exception.AppException;
import com.prprv.property.repo.sys.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Yoooum
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, String> redisTemplate;
    private final RestTemplate restTemplate;

    public User register(Register register) {
        checkIfUserExists(register.username(), register.email(), register.phone());
        User user = new User();
        user.setUsername(register.username());
        user.setEmail(register.email());
        user.setPhone(register.phone());
        user.setActivated(false);
        user.setPassword(passwordEncoder.encode(register.password()));
        try {
            user = userRepository.save(user);
            return user;
        } catch (Exception e) {
            log.info("注册失败", e);
            return null;
        }
    }

    /**
     * 找回密码
     * @param phone 手机号
     * @param code 验证码
     * @param password 密码
     *@return Boolean 结果
     */
    public User resetPassword(String phone, String code ,String password) throws IOException {
        String url = "http://localhost:8080/api/v1/verify/phone?code=" + code + "&phone=" + phone;
        String verifyString = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(verifyString);
        if (!jsonNode.path("data").path("valid").asBoolean()) {
            throw new AppException(E.SMSAUTHCODE_ERROR);
        }
        return userRepository.findByPhone(phone)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(password));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new AppException(E.UPDATE_FAILED));
    }

    @Transactional
    public boolean activateUser(String email,String activationCode) {
        String code = redisTemplate.opsForValue().get(email + ":code");
        if (!Objects.equals(code, activationCode)) {
            throw new AppException(E.CODE_EXPIRED);
        }
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Long expire = redisTemplate.getExpire(email + ":code");
            if (expire != null && expire > 0 && expire <= 5 * 60) {
                User user = optionalUser.get();
                user.setActivated(true);
                userRepository.save(user);
                redisTemplate.delete(email + ":code");
                return true;
            }
            throw new AppException(E.CODE_EXPIRED);
        }
        return false;
    }

    public void checkIfUserExists(String username, String email, String phone) {
        if (StringUtils.hasText(username))
            userRepository.findByUsername(username).ifPresent(action -> {
                throw new AppException(E.USERNAME_ALREADY_EXISTS);
            });
        if (StringUtils.hasText(email))
            userRepository.findByEmail(email).ifPresent(action -> {
                throw new AppException(E.EMAIL_ALREADY_EXISTS);
            });
        if (StringUtils.hasText(phone))
            userRepository.findByPhone(phone).ifPresent(action -> {
                throw new AppException(E.PHONE_ALREADY_EXISTS);
            });
    }
}
