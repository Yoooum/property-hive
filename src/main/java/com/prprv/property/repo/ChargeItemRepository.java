package com.prprv.property.repo;

import com.prprv.property.entity.biz.ChargeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface ChargeItemRepository extends JpaRepository<ChargeItem, Long> {
}
