package com.prprv.property.entity.sys;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

    /**
     * 用户名
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 手机号
     */
    @Column(unique = true)
    private String phone;

    /**
     * 邮箱
     */
    @Column(unique = true)
    private String email;

    /**
     * 用户角色
     */
    @ManyToOne
    private Role role;
}
