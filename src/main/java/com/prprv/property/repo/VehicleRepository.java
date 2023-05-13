package com.prprv.property.repo;

import com.prprv.property.entity.biz.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
