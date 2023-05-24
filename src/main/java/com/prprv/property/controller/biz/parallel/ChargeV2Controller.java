package com.prprv.property.controller.biz.parallel;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.parallel.ChargeV2;
import com.prprv.property.repo.parallel.ChargeV2Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/parallel/charge")
public class ChargeV2Controller extends AbstractCrudController<ChargeV2, ChargeV2Repository> {
    protected ChargeV2Controller(ChargeV2Repository repository) {
        super(repository);
    }
}
