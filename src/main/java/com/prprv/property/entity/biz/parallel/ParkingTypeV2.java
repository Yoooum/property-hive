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
public class ParkingTypeV2 extends AbstractEntity {
    private String community;
    private String parkName;
    private String licensePlate;
    private String vehicleMaster;
    private String contact;
    private String useType;
    private Double fee;
}
