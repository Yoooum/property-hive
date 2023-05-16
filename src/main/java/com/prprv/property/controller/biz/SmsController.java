package com.prprv.property.controller.biz;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.sys.User;
import com.prprv.property.repo.sys.UserRepository;
import com.prprv.property.service.SmsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * 发送短信验证码
 * @author Christine
 */

@RestController
@Slf4j
@RequestMapping("/api/v1/")
public class SmsController extends AbstractCrudController<User, UserRepository>{

    @Resource
    private SmsService smsService;

    protected SmsController(UserRepository repository) {
        super(repository);
    }

    /**
     * 发送验证码
     * @param phone 请求号码
     */
    @GetMapping("/sms")
    public R<Object> sendSms(@RequestParam String phone) {
        String pattern = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
//        号码校验
        if (!phone.matches(pattern)) {
            log.error("[短信服务]请求号码"+phone+"判为不正确的手机号码");
            return R.error(E.INTERNAL_SERVER_ERROR);
        }else {
//         校验号码是否已注册过
            Optional<User> existingUserByPhone = super.repository.findByUsername(phone);
            if (existingUserByPhone.isPresent()) {
                log.error("[短信服务]请求号码为"+phone+"的用户已存在");
                return R.error(E.PHONE_ALREADY_EXISTS);
            }
            String[] phoneformat = {phone};
            smsService.Sms(phoneformat);
        }
        return R.ok();
    }
}
