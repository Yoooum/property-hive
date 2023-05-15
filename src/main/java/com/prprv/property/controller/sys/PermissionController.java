package com.prprv.property.controller.sys;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.sys.Permission;
import com.prprv.property.repo.sys.PermissionRepository;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController extends AbstractCrudController<Permission, PermissionRepository> {

    protected PermissionController(PermissionRepository repository) {
        super(repository);
    }

    @GetMapping("/name/{name}")
    public R<Optional<Permission>> getByName(@PathVariable String name) {
        Permission permission = new Permission();
        permission.setName(name);
        Example<Permission> example = Example.of(permission);
        return R.ok(this.repository.findOne(example));
    }
}
