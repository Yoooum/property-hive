package com.prprv.property.controller.sys;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.sys.Role;
import com.prprv.property.entity.sys.User;
import com.prprv.property.entity.value.Register;
import com.prprv.property.exception.AppException;
import com.prprv.property.repo.sys.RoleRepository;
import com.prprv.property.repo.sys.UserRepository;
import com.prprv.property.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @Resource
    private RoleRepository roleRepository;
    protected UserController(UserRepository repository) {
        super(repository);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Register register) {
        return ResponseEntity.ok(userService.register(register));
    }

    /**
     *  激活用户
     * @param email 用户邮箱
     * @param activationCode 激活码
     * @return ResponseEntity 结果
     */
    @Operation(summary = "激活用户", description = "在实际项目中请不要直接调用接口，最好使用发送邮件")
    @GetMapping("/activate")
    public ResponseEntity<Boolean> activateUser(@RequestParam(value = "email") String email ,@RequestParam(value = "code") String activationCode) {
        return ResponseEntity.ok(userService.activateUser( email, activationCode));
    }

    @Override
    public R<User> create(User entity) {
        return null;
    }

    @PutMapping("/{id}")
    @Override
    public R<User> update(@PathVariable Long id, @RequestBody User entity) {
        Optional<User> existingUserByUsername = super.repository.findByUsername(entity.getUsername());
        Optional<User> existingUserByEmail = super.repository.findByEmail(entity.getEmail());
        Optional<User> existingUserByPhone = super.repository.findByPhone(entity.getPhone());
        if (entity.getUsername() != null)
            if (existingUserByUsername.isPresent() && !existingUserByUsername.get().getId().equals(id))
                throw new AppException(E.USERNAME_ALREADY_EXISTS);
        if (entity.getEmail() != null)
            if (existingUserByEmail.isPresent() && !existingUserByEmail.get().getId().equals(id))
                throw new AppException(E.EMAIL_ALREADY_EXISTS);
        if (entity.getPhone() != null)
            if (existingUserByPhone.isPresent() && !existingUserByPhone.get().getId().equals(id))
                throw new AppException(E.PHONE_ALREADY_EXISTS);
        if (entity.getPassword() != null)
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        Optional<User> existingUser = super.repository.findById(id);


        if (existingUser.isPresent()) {
            existingUser.get().setUsername(entity.getUsername());
            existingUser.get().setEmail(entity.getEmail());
            existingUser.get().setPhone(entity.getPhone());
            Optional<Role> role = roleRepository.findById(entity.getRole().getId());
            if (role.isEmpty())
                return R.error(E.NOT_FOUND, "更新失败，没有此角色id");
            return R.ok(super.repository.saveAndFlush(existingUser.get()));
        }
        return R.error(E.NOT_FOUND, "更新失败，用户id不存在");
    }
}
