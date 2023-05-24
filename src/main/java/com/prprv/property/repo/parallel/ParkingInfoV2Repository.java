package com.prprv.property.repo.parallel;

import com.prprv.property.entity.biz.parallel.ParkingInfoV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface ParkingInfoV2Repository extends JpaRepository<ParkingInfoV2, Long> {
}
