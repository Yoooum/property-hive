package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Owner;
import com.prprv.property.repo.OwnerRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController extends AbstractCrudController<Owner, OwnerRepository> {

    protected OwnerController(OwnerRepository repository) {
        super(repository);
    }
}
