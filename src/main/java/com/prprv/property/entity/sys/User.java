package com.prprv.property.entity.sys;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户表
 * @author Yoooum
 */
@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

    /**
     * 用户名
     */
    @Column(unique = true)
    private String username;

    /**
     * 密码
     */
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

    /**
     * 是否激活
     */
    @Column(columnDefinition = "boolean default false" ,nullable = false)
    private Boolean activated;

}
