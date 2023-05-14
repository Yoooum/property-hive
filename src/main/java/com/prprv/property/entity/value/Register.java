package com.prprv.property.entity.value;

/**
 * 注册信息类型
 * @author Yoooum
 */
public record Register(
        String username,
        String email,
        String phone,
        String password
) {
}
