package com.prprv.property.entity.sys;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 角色表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractEntity {
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标识
     */
    private String code;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 角色权限
     */
    @ManyToMany
    private List<Permission> permission;
}
