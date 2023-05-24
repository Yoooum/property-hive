package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 房产表
 *
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Property extends AbstractEntity {

    /**
     * 所属小区
     */
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    private Long id;
    private String name;


    private String owner;
    private String unit;
    private String contact;
    private Integer rooms;
    private Integer floors;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime checkIn;
}
