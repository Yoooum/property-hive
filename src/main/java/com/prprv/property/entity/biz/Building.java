package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 楼栋表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Building extends AbstractEntity {

    /**
     * 所属小区
     */
    @ManyToOne
    private Community community;

    /**
     * 楼栋名称
     */
    private String name;

    /**
     * 总户数
     */
    private Integer rooms;

    /**
     * 描述
     */
    private String description;

}
