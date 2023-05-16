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
import com.prprv.property.service.SmsService;
import com.prprv.property.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yoooum
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController extends AbstractCrudController<User, UserRepository> {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserService userService;
    @Resource
    private RoleRepository roleRepository;

    @Resource
    private SmsService smsService;

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

    /**
     * 使用手机验证码注册.
     * <p>1.传入验证码后对比验证码是否匹配</p>
     * <p>2.匹配后将手机号码作为用户名和用户电话创建用户,初始密码为123456</p>
     * @param authcode 验证码
     * @see SmsService
     * @see java.util.regex.Pattern
     * @see java.util.regex.Matcher
     */
    @PostMapping("/register/phone")
    public R<User> registerByPhone(@RequestParam String authcode) {
//        匹配验证码
        if (!Objects.equals(authcode, smsService.authcode))
            return R.error(E.SMSAUTHCODE_ERROR,"验证码错误,请重新输入验证码");

//        正则表达式提取数字
        String pattern = "[0-9]+";
//        创建 Pattern 对象:pattern 对象是一个正则表达式的编译表示.
        Pattern r = Pattern.compile(pattern);
//        创建 Pattern 对象:Matcher 对象是对输入字符串进行解释和匹配操作的引擎.
        Matcher m = r.matcher(smsService.cellphone);
//        查找字符串中是否有匹配正则表达式的字符/字符串
        if (m.find()) {
//        电话号码校验，此处判断恒定为真
            log.info("Found value: " + m.group(0));
        } else {
            return R.error(E.INTERNAL_SERVER_ERROR, "电话号码校验失败");
        }
//        去掉原数据中的"[]"
        String cellphone = m.group(0);
//        创建用户
        User user = new User();
        user.setUsername(cellphone);
        user.setPhone(cellphone);
        user.setPassword(passwordEncoder.encode("123456"));
//        保存用户
        return R.ok(super.repository.saveAndFlush(user));
    }

}
