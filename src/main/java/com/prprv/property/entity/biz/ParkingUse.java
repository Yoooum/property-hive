package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 停车位使用表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ParkingUse extends AbstractEntity {

    /**
     * 所属停车位
     */
    @ManyToOne
    private Parking parking;

    /**
     * 车辆信息
     */
    @ManyToOne
    private Vehicle vehicle;

    /**
     * 使用类型（0=租赁 / 1=销售）
     */
    private Integer type;

    /**
     * 费用
     */
    private Double price;

    /**
     * 开始时间
     */
    private LocalDateTime startAt;

    /**
     * 结束时间
     */
    private LocalDateTime endAt;
}
