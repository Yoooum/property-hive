package com.prprv.property.controller.biz.parallel;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.parallel.PropertyFeeV2;
import com.prprv.property.repo.parallel.PropertyFeeV2Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/parallel/property/fee")
public class PropertyFeeV2Controller extends AbstractCrudController<PropertyFeeV2, PropertyFeeV2Repository> {
    protected PropertyFeeV2Controller(PropertyFeeV2Repository repository) {
        super(repository);
    }
}
