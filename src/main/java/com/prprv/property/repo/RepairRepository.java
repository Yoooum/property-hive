package com.prprv.property.repo;

import com.prprv.property.entity.biz.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yoooum
 */
@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
    List<Repair> findByIdLikeOrNameLike(Long id, String name);
}
