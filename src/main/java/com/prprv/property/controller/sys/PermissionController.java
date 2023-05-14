package com.prprv.property.controller.sys;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.sys.Permission;
import com.prprv.property.repo.sys.PermissionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController extends AbstractCrudController<Permission, PermissionRepository> {

    protected PermissionController(PermissionRepository repository) {
        super(repository);
    }
}