package com.prprv.property.entity.biz.parallel;

import com.prprv.property.entity.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 访客表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class VisitorV2 extends AbstractEntity {
    private String name;
    private String contact;
    private String community;
    private String communityName;
    private Boolean stay;
    private String idCard;
    private Integer ParkFee;
}
