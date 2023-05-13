package com.prprv.property.repo;

import com.prprv.property.entity.biz.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
