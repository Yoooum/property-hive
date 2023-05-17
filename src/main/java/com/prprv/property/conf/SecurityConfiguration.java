package com.prprv.property.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.filter.TokenAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.List;

/**
 * @author Yoooum
 */
@Slf4j
@Configuration
public class SecurityConfiguration {
    private final TokenAuthenticationFilter tokenFilter;
    private final ObjectMapper objectMapper;

    public SecurityConfiguration(TokenAuthenticationFilter tokenFilter, ObjectMapper objectMapper) {
        this.tokenFilter = tokenFilter;
        this.objectMapper = objectMapper;
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 身份认证管理器
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 关闭csrf
                .csrf().disable()
                // 关闭默认页面，自己实现登入、登出
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()

                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                // 请求认证
                .authorizeHttpRequests()
                // 不需要身份认证的请求
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/doc.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/api/v1/auth/authorize",
                        "/api/v1/auth/token",
                        "/api/v1/sms",
                        "/api/v1/user/register/phone",
                        "/api/v1/user/register",
                        "/api/v1/user")
                .permitAll()
                // 其他所有请求都需要登录
                .anyRequest().permitAll()
                .and()
                // 异常处理
                .exceptionHandling()
                // 未登录时，返回401
                .authenticationEntryPoint(this.authenticationEntryPoint())
                // 无权限时，返回403
                .accessDeniedHandler(this.accessDeniedHandler())
                .and()
                // Token过滤器
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (req, resp, e) -> {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.setContentType("application/json;charset=UTF-8");
            try {
                resp.getWriter().write(objectMapper.writeValueAsString(R.error(E.FORBIDDEN, e.getMessage())));
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (req, resp, e) -> {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setContentType("application/json;charset=UTF-8");
            try {
                resp.getWriter().write(objectMapper.writeValueAsString(R.error(E.UNAUTHORIZED, e.getMessage())));
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
