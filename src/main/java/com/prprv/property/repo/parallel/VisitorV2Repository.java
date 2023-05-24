package com.prprv.property.repo.parallel;

import com.prprv.property.entity.biz.parallel.VisitorV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface VisitorV2Repository extends JpaRepository<VisitorV2, Long> {
}
