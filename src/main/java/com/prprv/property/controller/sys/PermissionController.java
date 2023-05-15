package com.prprv.property.controller.sys;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.sys.Permission;
import com.prprv.property.repo.sys.PermissionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController extends AbstractCrudController<Permission, PermissionRepository> {

    protected PermissionController(PermissionRepository repository) {
        super(repository);
    }

    @GetMapping()
    public R<List<Permission>> getByName(String name, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Permission permission = new Permission();
        permission.setName(name.trim());
        return R.ok(super.getByTarget(permission, "name", fuzzy));
    }
}
