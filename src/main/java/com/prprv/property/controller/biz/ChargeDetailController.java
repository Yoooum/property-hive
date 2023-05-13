package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.ChargeDetail;
import com.prprv.property.repo.ChargeDetailRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/charge/detail")
public class ChargeDetailController extends AbstractCrudController<ChargeDetail, ChargeDetailRepository> {

    protected ChargeDetailController(ChargeDetailRepository repository) {
        super(repository);
    }
}
