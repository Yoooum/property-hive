package com.prprv.property.common.token;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 令牌提供类
 * @author Yoooum
 */
@Component
public class TokenProvider {
    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    // 密钥
    @Value("${token.secret}")
    private String secret;

    // 访问令牌的有效时间
    @Value("${token.ttl.access}")
    private Long accessTTL;

    // 刷新令牌的有效时间
    @Value("${token.ttl.refresh}")
    private Long refreshTTL;

    public record Token(String accessToken, String refreshToken) {
    }

    /**
     * 创建访问令牌和刷新令牌
     *
     * @param subject 令牌主题（通常是用户 ID）
     * @param claims  令牌中要包含的其他数据
     * @return 新创建的访问令牌，如果创建失败则返回 null
     */
    public Token createToken(String subject, Map<String, Object> claims) {
        String accessToken = createToken(subject, claims, accessTTL);
        String refreshToken = createToken(subject, claims, refreshTTL);
        return new Token(accessToken, refreshToken);
    }

    /**
     * 创建 JWT 令牌
     *
     * @param subject 令牌主题（通常是用户名或ID）
     * @param claims  令牌中要包含的其他数据
     * @param ttl     令牌的有效期（秒）
     * @return 新创建的令牌，如果创建失败则返回 null
     */
    public String createToken(String subject, Map<String, Object> claims, Long ttl) {
        Date now = new Date();
        long expire = now.getTime() + ttl * 1000;
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder()
                .subject(subject)
                .issueTime(now)
                .expirationTime(new Date(expire))
                // 标识JWT唯一性
                .jwtID(UUID.randomUUID().toString());
        if (claims != null) {
            claims.forEach(builder::claim);
        }
        JWTClaimsSet claimsSet = builder.build();
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        try {
            signedJWT.sign(new MACSigner(secret.getBytes()));
        } catch (JOSEException e) {
            log.error("Token signing failed: {}", e.getMessage());
            return null;
        }
        return signedJWT.serialize();
    }

    /**
     * 解析令牌
     * @param token 带解析的 JWT 令牌
     * @return 解析后的 JWT 令牌声明集合，解析失败返回 null
     */
    public JWTClaimsSet parseToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            // 验签
            MACVerifier verifier = new MACVerifier(secret.getBytes());
            if (signedJWT.verify(verifier)) {
                return signedJWT.getJWTClaimsSet();
            }
        } catch (ParseException | JOSEException e) {
            log.error("Token extraction failed: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 是过期令牌
     * @param claimsSet 声明集
     * @return 是否过期
     */
    public boolean isExpiredToken(JWTClaimsSet claimsSet) {
        if (claimsSet == null) {
            log.error("ClaimsSet is null");
            return true;
        }
        return claimsSet.getExpirationTime().before(new Date());
    }

}
