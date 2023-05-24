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
public class ParkingInfoV2 extends AbstractEntity {
    private String name;
    private String status;
    private String community;
}
