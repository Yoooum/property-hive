package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收费项目
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ChargeItem extends AbstractEntity {

    /**
     * 所属小区
     */
    @ManyToOne
    private Community community;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 收费编号
     */
    private String code;
}
