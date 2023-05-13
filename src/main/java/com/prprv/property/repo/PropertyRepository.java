package com.prprv.property.repo;

import com.prprv.property.entity.biz.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
