package com.prprv.property.repo.parallel;

import com.prprv.property.entity.biz.parallel.ChargeV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface ChargeV2Repository extends JpaRepository<ChargeV2, Long> {
}
