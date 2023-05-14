package com.prprv.property.controller.sys;

import com.prprv.property.common.response.R;
import com.prprv.property.entity.value.SignIn;
import com.prprv.property.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthorizedController {

    @Resource
    private TokenService tokenService;
    @PostMapping("/token")
    public ResponseEntity<R<Object>> createToken(@RequestBody SignIn sign) {
        return tokenService.createToken(sign);
    }

    @GetMapping("/refresh")
    public ResponseEntity<R<Object>> refreshToken(@RequestParam String refreshToken) {
        return tokenService.refreshToken(refreshToken);
    }
}
