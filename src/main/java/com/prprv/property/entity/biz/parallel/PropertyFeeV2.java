package com.prprv.property.entity.biz.parallel;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PropertyFeeV2 extends AbstractEntity {
    private String username;
    private String contact;
    private String address;
    private String status;
    private Double amount;
}
