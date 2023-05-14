package com.prprv.property.service;

import com.prprv.property.entity.sys.Role;
import com.prprv.property.entity.sys.User;
import com.prprv.property.repo.sys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoooum
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // 设置权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        Role role = user.getRole();
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
            role.getPermission().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getCode())));
        }
        // 返回一个 UserDetails 实现类，这里使用 Security 自带的 User
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
