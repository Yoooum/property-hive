package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Repair;
import com.prprv.property.repo.RepairRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/repair")
public class RepairController extends AbstractCrudController<Repair, RepairRepository> {

    protected RepairController(RepairRepository repository) {
        super(repository);
    }
}
