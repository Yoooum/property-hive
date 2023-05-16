package com.prprv.property.service;

import com.prprv.property.common.response.E;
import com.prprv.property.entity.sys.User;
import com.prprv.property.entity.value.Register;
import com.prprv.property.exception.AppException;
import com.prprv.property.repo.sys.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Yoooum
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(Register register) {
        checkIfUserExists(register.username(), register.email(), register.phone());
        User user = new User();
        user.setUsername(register.username());
        user.setEmail(register.email());
        user.setPhone(register.phone());
        user.setPassword(passwordEncoder.encode(register.password()));
        return userRepository.save(user);
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
