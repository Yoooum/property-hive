package com.prprv.property.repo;

import com.prprv.property.entity.biz.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
}
