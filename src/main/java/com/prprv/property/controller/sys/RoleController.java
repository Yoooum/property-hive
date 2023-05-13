package com.prprv.property.controller.sys;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.sys.Role;
import com.prprv.property.repo.sys.RoleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/role")
public class RoleController extends AbstractCrudController<Role, RoleRepository> {

    protected RoleController(RoleRepository repository) {
        super(repository);
    }
}
