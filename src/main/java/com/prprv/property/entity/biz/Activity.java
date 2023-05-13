package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 活动表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Activity extends AbstractEntity {
    /**
     * 所属小区
     */
    @ManyToOne
    private Community community;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 活动地址
     */
    private String address;
    /**
     * 举办单位
     */
    private String sponsor;
    /**
     * 活动开始时间
     */
    private LocalDateTime startAt;
    /**
     * 活动截止时间
     */
    private LocalDateTime endAt;
}
