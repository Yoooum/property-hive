package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 访客表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Visitor extends AbstractEntity {
    /**
     * 访客姓名
     */
    private String name;
    /**
     * 访问小区
     */
    @ManyToOne
    private Community community;
    /**
     * 访问时间
     */
    private String visitTime;
}
