package com.prprv.property.repo;

import com.prprv.property.entity.biz.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
