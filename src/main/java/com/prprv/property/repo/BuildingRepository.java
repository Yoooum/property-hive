package com.prprv.property.repo;

import com.prprv.property.entity.biz.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
