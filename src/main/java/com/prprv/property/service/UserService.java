package com.prprv.property.service;

import com.prprv.property.entity.sys.User;
import com.prprv.property.entity.value.Register;
import com.prprv.property.repo.sys.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        User user = new User();
        user.setUsername(register.username());
        user.setEmail(register.email());
        user.setPhone(register.phone());
        user.setPassword(passwordEncoder.encode(register.password()));
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }
}
