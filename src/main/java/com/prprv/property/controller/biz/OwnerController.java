package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Owner;
import com.prprv.property.repo.OwnerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController extends AbstractCrudController<Owner, OwnerRepository> {

    protected OwnerController(OwnerRepository repository) {
        super(repository);
    }

    @GetMapping()
    public R<List<Owner>> getByName(String name, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Owner target = new Owner();
        target.setName(name.trim());
        return R.ok(super.getByTarget(target, "name", fuzzy));
    }

}
