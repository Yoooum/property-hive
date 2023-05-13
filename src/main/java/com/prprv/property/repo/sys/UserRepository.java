package com.prprv.property.repo.sys;

import com.prprv.property.entity.sys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
