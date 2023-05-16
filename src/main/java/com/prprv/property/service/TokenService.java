package com.prprv.property.service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.common.token.TokenProvider;
import com.prprv.property.entity.sys.User;
import com.prprv.property.entity.value.SignIn;
import com.prprv.property.repo.sys.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Yoooum
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;
    public ResponseEntity<R<Object>> createToken(SignIn signIn) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(signIn.username(), signIn.password());
        Optional<User> user = userRepository.findByUsername(signIn.username());
        Boolean activated = user.map(User::getActivated).orElse(false);
        if (!activated) {
            return ResponseEntity.status(401).body(R.error(E.UNAUTHORIZED, "账号未激活"));
        }
        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            String name = authenticate.getName();
            Collection<? extends GrantedAuthority> authorities = authenticate.getAuthorities();
            Map<String, Object> map = new HashMap<>();
            map.put("authorities", authorities);
            return ResponseEntity.ok(R.ok(tokenProvider.createToken(name, map)));
        } catch (AuthenticationException e) {
            log.error("Authentication failed: {}", e.getMessage());
            return ResponseEntity.status(401).body(R.error(E.UNAUTHORIZED, e.getMessage()));
        }
    }

    public ResponseEntity<R<Object>> refreshToken(String refreshToken) {
        JWTClaimsSet claimsSet = tokenProvider.parseToken(refreshToken);
        if (tokenProvider.isExpiredToken(claimsSet)) {
            return ResponseEntity.status(401).body(R.error(E.UNAUTHORIZED));
        }
        String subject = claimsSet.getSubject();
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Map<String, Object> map = new HashMap<>();
            map.put("authorities", userDetails.getAuthorities());
            return ResponseEntity.ok(R.ok(tokenProvider.createToken(subject, map)));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(R.error(E.UNAUTHORIZED));
        }
    }
}
