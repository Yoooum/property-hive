package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 业主表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Owner extends AbstractEntity {

    /**
     * 业主名称
     */
    private String name;

    /**
     * 所属小区
     */
    @ManyToOne
    private Community community;

    /**
     * 所属房产
     */
    @ManyToOne
    private Property property;

    /**
     * 身份证号
     */
    private String identityCard;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 职业
     */
    private String job;

    /**
     * 性别
     */
    private String sex;

    /**
     * 备注
     */
    private String remark;
    /**
     * 入住时间
     */
    private Date checkIn;

}
