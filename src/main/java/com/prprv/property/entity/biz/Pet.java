package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import com.prprv.property.entity.sys.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Pet extends AbstractEntity {

    /**
     * 所属成员
     */
    @ManyToOne
    private User user;

    /**
     * 宠物名称
     */
    private String name;

    /**
     * 宠物照片
     */
    private String photo;

    /**
     * 宠物颜色
     */
    private String color;

    /**
     * 备注
     */
    private String remark;
}
