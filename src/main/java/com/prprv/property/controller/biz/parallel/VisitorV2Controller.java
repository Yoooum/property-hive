package com.prprv.property.controller.biz.parallel;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.parallel.VisitorV2;
import com.prprv.property.repo.parallel.VisitorV2Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/parallel/visitor")
public class VisitorV2Controller extends AbstractCrudController<VisitorV2, VisitorV2Repository> {
    protected VisitorV2Controller(VisitorV2Repository repository) {
        super(repository);
    }
}
