package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 收费明细表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ChargeDetail extends AbstractEntity {

    /**
     * 所属小区
     */
    @ManyToOne
    private Community community;

    /**
     * 所属项目
     */
    @ManyToOne
    private ChargeItem chargeItem;

    /**
     * 业主
     */
    @ManyToOne
    private Owner owner;

    /**
     * 应收金额
     */
    private Double amount;

    /**
     * 实收金额
     */
    private Double actualAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 缴费时间
     */
    private LocalDateTime payAt;
}
