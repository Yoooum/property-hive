package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import com.prprv.property.entity.sys.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 保修表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Repair extends AbstractEntity {

    /**
     * 所属小区
     */
    @ManyToOne
    private Community community;

    /**
     * 报修人员
     */
    @ManyToOne
    private User user;

    /**
     * 报修项目名称
     */
    private String name;

    /**
     * 报修描述
     */
    private String description;

    /**
     * 状态（0=待处理 / 1=已处理）
     */
    private Integer status;
}
