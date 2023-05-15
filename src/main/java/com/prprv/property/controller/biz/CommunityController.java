package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Community;
import com.prprv.property.repo.CommunityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/community")
public class CommunityController extends AbstractCrudController<Community, CommunityRepository> {

    protected CommunityController(CommunityRepository repository) {
        super(repository);
    }

    @GetMapping()
    public R<List<Community>> getByName(String name, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Community target = new Community();
        target.setName(name.trim());
        return R.ok(super.getByTarget(target, "name", fuzzy));
    }
}
