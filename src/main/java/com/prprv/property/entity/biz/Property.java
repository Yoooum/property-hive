package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 房产表
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
    private Community community;

    private Long id;
    private String name;

    @ManyToOne
    private Owner owner;

    private String contact;
    private Integer rooms;
    private Integer floors;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime checkIn;
}
