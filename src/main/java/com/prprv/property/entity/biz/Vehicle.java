package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import com.prprv.property.entity.sys.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车辆表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Vehicle extends AbstractEntity {

    /**
     * 车牌号
     */
    private String licensePlate;

    /**
     * 车主
     */
    @ManyToOne
    private User user;

    /**
     * 车辆颜色
     */
    private String color;

    /**
     * 备注
     */
    private String remark;

}
