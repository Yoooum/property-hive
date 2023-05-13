package com.prprv.property.repo;

import com.prprv.property.entity.biz.ChargeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface ChargeDetailRepository extends JpaRepository<ChargeDetail, Long> {
}
