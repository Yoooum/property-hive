package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Visitor;
import com.prprv.property.repo.VisitorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/visitor")
public class VisitorController extends AbstractCrudController<Visitor, VisitorRepository> {

    protected VisitorController(VisitorRepository repository) {
        super(repository);
    }
}
