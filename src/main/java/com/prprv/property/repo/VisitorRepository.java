package com.prprv.property.repo;

import com.prprv.property.entity.biz.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
