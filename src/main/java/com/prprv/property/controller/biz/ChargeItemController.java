package com.prprv.property.controller.biz;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.ChargeItem;
import com.prprv.property.repo.ChargeItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 根据楼栋名称查询楼栋信息
     * @param name 楼栋名称
     * @return R 楼栋信息
     */
    @GetMapping("/name/{name}")
    public R<List<ChargeItem>> getByName(@PathVariable String name) {
        ChargeItem chargeItem = new ChargeItem();
        chargeItem.setName(name);
        Example<ChargeItem> example = Example.of(chargeItem);
        List<ChargeItem> chargeItemList = this.repository.findAll(example);
        return chargeItemList.isEmpty() ? R.error(E.NOT_FOUND) : R.ok(chargeItemList);
    }
}
