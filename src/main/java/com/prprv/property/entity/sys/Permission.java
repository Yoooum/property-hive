package com.prprv.property.entity.sys;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Permission extends AbstractEntity {
    /**
     * 权限标识
     */
    private String code;
    /**
     * 权限名称
     */
    private String name;
}
