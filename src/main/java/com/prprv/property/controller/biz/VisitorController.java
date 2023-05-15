package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Visitor;
import com.prprv.property.repo.VisitorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/visitor")
public class VisitorController extends AbstractCrudController<Visitor, VisitorRepository> {

    protected VisitorController(VisitorRepository repository) {
        super(repository);
    }

    /**
     * 根据访客名称查询访客信息
     *
     * @param name 访客名称
     * @return R 访客信息
     */
     @GetMapping()
    public R<List<Visitor>> getByName(String name, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Visitor target = new Visitor();
        target.setName(name.trim());
        return R.ok(super.getByTarget(target, "name", fuzzy));
    }
}
