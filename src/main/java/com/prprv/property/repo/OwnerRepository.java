package com.prprv.property.repo;

import com.prprv.property.entity.biz.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
