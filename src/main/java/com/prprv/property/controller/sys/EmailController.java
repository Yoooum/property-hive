package com.prprv.property.controller.sys;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.exception.AppException;
import com.prprv.property.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phj233
 * @since 2023/5/16 15:02
 */
@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {
    final private EmailService emailService;

    /**
     * 发送激活邮件
     * @param email 邮箱
     * @param code 激活码
     * @return R 结果
     */
    @Operation(summary = "发送激活邮件")
    @GetMapping("/send")
    public R<Object> sendEmail(@RequestParam(value = "email") String email,
                               @RequestParam(value = "code") String code) {
        try {
            emailService.sendActivationEmail(email, code);
        }catch (AppException e) {
            return R.error(E.EMAIL_SEND_FAILED,e.getMessage());
        }
        return R.ok();
    }

    /**
     * 获取激活码
     * @param email 邮箱
     * @return R 结果
     */
    @Operation(summary = "获取激活码")
    @GetMapping("/getCode")
    public R<Object> getCode(@RequestParam(value = "email") String email) {
        String code = emailService.getActivationCode(email);
        return R.ok(code);
    }
}
