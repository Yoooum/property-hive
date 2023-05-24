package com.prprv.property.repo.parallel;

import com.prprv.property.entity.biz.parallel.PropertyFeeV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface PropertyFeeV2Repository extends JpaRepository<PropertyFeeV2, Long> {
}
