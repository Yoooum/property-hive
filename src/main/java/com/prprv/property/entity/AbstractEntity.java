package com.prprv.property.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 抽象实体类。
 * <p>
 * {@link Data}
 * - lombok注解，自动添加getter、setter、toString、equals、hashCode等方法。
 * {@link MappedSuperclass}
 * - jpa注解标识父类。标注的类将不是一个完整的实体类，不会映射到数据库表，但是它的属性都将映射到子类的数据库字段中。
 * <p>
 * {@link AuditingEntityListener}
 * - 实体监听器，用于自动填充创建时间 {@link CreatedDate} 、更新时间 {@link LastModifiedDate} 等字段。
 * 需要在配置类中添加 {@link EnableJpaAuditing} 注解才能生效。
 * <p>
 * {@link JsonIgnoreProperties}
 * - jackson注解，用于忽略json序列化时的某些属性。
 * {@code @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})}
 * - 忽略hibernateLazyInitializer、handler属性。直接使用bean时，避免json序列号失败。
 *
 * @author Yoooum
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public abstract class AbstractEntity implements Serializable {

    /**
     * 主键生成策略，由数据库自动生成。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * 自动填充创建时间。
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDateTime createAt;

    /**
     * 自动填充更新时间。
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDateTime updateAt;
}
