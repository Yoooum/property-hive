package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.ChargeItem;
import com.prprv.property.repo.ChargeItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/charge/item")
public class ChargeItemController extends AbstractCrudController<ChargeItem, ChargeItemRepository> {

    protected ChargeItemController(ChargeItemRepository repository) {
        super(repository);
    }

    @GetMapping()
    public R<List<ChargeItem>> getByName(String name, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        ChargeItem target = new ChargeItem();
        try {
            target.setId(Long.parseLong(name));
        } catch (NumberFormatException e) {
            target.setName(name.trim());
        }
        return R.ok(super.getByTarget(target, "name", fuzzy));
    }
}
