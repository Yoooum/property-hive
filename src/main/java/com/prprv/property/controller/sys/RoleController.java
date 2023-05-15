package com.prprv.property.controller.sys;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.sys.Role;
import com.prprv.property.repo.sys.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/role")
public class RoleController extends AbstractCrudController<Role, RoleRepository> {

    protected RoleController(RoleRepository repository) {
        super(repository);
    }
    @GetMapping()
    public R<List<Role>> getByName(String name, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Role target = new Role();
        target.setName(name.trim());
        return R.ok(super.getByTarget(target, "name", fuzzy));
    }
}
