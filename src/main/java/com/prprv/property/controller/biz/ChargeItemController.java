package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.ChargeItem;
import com.prprv.property.repo.ChargeItemRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/charge/item")
public class ChargeItemController extends AbstractCrudController<ChargeItem, ChargeItemRepository> {

    protected ChargeItemController(ChargeItemRepository repository) {
        super(repository);
    }
}
