package com.prprv.property.repo;

import com.prprv.property.entity.biz.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
