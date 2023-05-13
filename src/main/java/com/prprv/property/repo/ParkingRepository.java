package com.prprv.property.repo;

import com.prprv.property.entity.biz.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
