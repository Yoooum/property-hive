package com.prprv.property.controller.sys;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.sys.User;
import com.prprv.property.entity.value.Register;
import com.prprv.property.repo.sys.UserRepository;
import com.prprv.property.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractCrudController<User, UserRepository> {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserService userService;

    protected UserController(UserRepository repository) {
        super(repository);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Register register) {
        return ResponseEntity.ok(userService.register(register));
    }

    @Override
    public R<User> update(Long id, User entity) {
        if (entity.getPassword() != null)
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.update(id, entity);
    }
}
