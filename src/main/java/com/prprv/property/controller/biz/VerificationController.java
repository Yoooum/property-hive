package com.prprv.property.controller.biz;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.entity.sys.User;
import com.prprv.property.repo.sys.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

/**
 * @author Yoooum
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/verify")
public class VerificationController {
    @Resource
    RestTemplate restTemplate;

    @Resource
    UserRepository userRepository;
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

    record Valid(Boolean valid, String message) {
    }

    @GetMapping("/email")
    public Object verifyEmail(@RequestParam String code, @RequestParam String email) {
        ResponseEntity<Valid> entity = restTemplate.getForEntity(url + "/email?email=" + email + "&code=" + code, Valid.class);
        Valid valid = entity.getBody();
        if (valid != null) {
            if (valid.valid) {
                Optional<User> byEmail = userRepository.findByEmail(email);
                if (byEmail.isPresent()) {
                    byEmail.get().setActivated(true);
                    userRepository.saveAndFlush(byEmail.get());
                    return R.ok(valid);
                }
                return R.error(E.NOT_FOUND, "邮箱未注册");
            }
            return R.ok(valid);

        }
        return R.ok(entity);
    }

    @GetMapping("/phone")
    public Object verifyPhone(@RequestParam String code, @RequestParam String phone) {
        ResponseEntity<Valid> entity = restTemplate.getForEntity(url + "/phone?phone=" + phone + "&code=" + code, Valid.class);
        Valid valid = entity.getBody();
        if (valid != null) {
            if (valid.valid) {
                Optional<User> byPhone = userRepository.findByPhone(phone);
                if (byPhone.isPresent()) {
                    byPhone.get().setActivated(true);
                    userRepository.saveAndFlush(byPhone.get());
                    return R.ok(valid);
                }
                return R.error(E.NOT_FOUND, "手机未注册");
            }
            return R.ok(valid);

        }
        return R.ok(entity);
    }
}
