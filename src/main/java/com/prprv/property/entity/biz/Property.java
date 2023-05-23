package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
    private Community community;

    private Long id;
    private String name;


    private String owner;
    private String unit;
    private String contact;
    private Integer rooms;
    private Integer floors;
    private String description;
    private Date checkIn;
}
