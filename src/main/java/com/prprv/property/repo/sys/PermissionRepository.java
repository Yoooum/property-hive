package com.prprv.property.repo.sys;

import com.prprv.property.entity.sys.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
