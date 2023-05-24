package com.prprv.property.repo.parallel;

import com.prprv.property.entity.biz.parallel.RepairV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface RepairV2Repository extends JpaRepository<RepairV2, Long> {
}
