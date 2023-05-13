package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 小区表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Community extends AbstractEntity {
    /**
     * 小区编号
     */
    private String code;
    /**
     * 小区名称
     */
    private String name;
    /**
     * 坐落地址
     */
    private String address;
    /**
     * 占地面积
     */
    private Double area;
    /**
     * 总栋数
     */
    private Integer floors;
    /**
     * 总户数
     */
    private Integer rooms;
    /**
     * 绿化率
     */
    private Double greenRate;
    /**
     * 状态
     */
    private String status;
}
