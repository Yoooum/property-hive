package com.prprv.property.entity.biz;

import com.prprv.property.entity.AbstractEntity;
import com.prprv.property.entity.sys.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 投诉表
 * @author Yoooum
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Complaint extends AbstractEntity {

    /**
     * 所属小区
     */
    @ManyToOne
    private Community community;

    /**
     * 投诉人员
     */
    @ManyToOne
    private User user;

    /**
     * 标题
     */
    private String title;

    /**
     * 事由
     */
    private String content;

    /**
     * 是否匿名 (True=匿名 False=不匿名)
     */
    private Boolean anonymous;
}
