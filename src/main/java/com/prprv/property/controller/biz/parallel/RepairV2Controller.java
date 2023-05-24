package com.prprv.property.controller.biz.parallel;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.parallel.RepairV2;
import com.prprv.property.repo.parallel.RepairV2Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/parallel/repair")
public class RepairV2Controller extends AbstractCrudController<RepairV2, RepairV2Repository> {
    protected RepairV2Controller(RepairV2Repository repository) {
        super(repository);
    }
}
