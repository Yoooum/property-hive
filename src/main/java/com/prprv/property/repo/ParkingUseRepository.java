package com.prprv.property.repo;

import com.prprv.property.entity.biz.ParkingUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface ParkingUseRepository extends JpaRepository<ParkingUse, Long> {
}
