package com.prprv.property.controller.biz;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Yoooum
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/verify")
public class VerificationController {
    @Resource
    RestTemplate restTemplate;
    String url = "http://10.10.0.1:58080/verify";

    @PostMapping("/email")
    public Object email(String email) {
        ResponseEntity<?> entity = restTemplate.postForEntity(url + "/email?email=" + email, null, Map.class);
        return entity.getBody();
    }

    @PostMapping("/phone")
    public Object phone(String phone) {
        ResponseEntity<?> entity = restTemplate.postForEntity(url + "/phone?phone=" + phone, null, Map.class);
        return entity.getBody();
    }

    record Valid(Boolean valid, String message) {}
    @GetMapping("/email")
    public Object verifyEmail(@RequestParam String code, @RequestParam String email) {
        ResponseEntity<Valid> entity = restTemplate.getForEntity(url + "/email?email=" + email + "&code=" + code, Valid.class);
        Valid valid = entity.getBody();
        if (valid!=null && valid.valid) {
            log.info("[邮箱验证]邮箱验证成功");
            return "成功";
        }
        return "失败";
    }

    @GetMapping("/phone")
    public Object verifyPhone(@RequestParam String code, @RequestParam String phone) {
        ResponseEntity<Valid> entity = restTemplate.getForEntity(url + "/phone?phone=" + phone + "&code=" + code, Valid.class);
        Valid valid = entity.getBody();
        if (valid!=null && valid.valid) {
            log.info("[手机验证]手机验证成功");
            return "成功";
        }
        return "失败";
    }
}
