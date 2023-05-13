package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

}
