package com.prprv.property.repo.parallel;

import com.prprv.property.entity.biz.parallel.ParkingTypeV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface ParkingTypeV2Repository extends JpaRepository<ParkingTypeV2, Long> {
}
