package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Building;
import com.prprv.property.repo.BuildingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/building")
public class BuildingController extends AbstractCrudController<Building, BuildingRepository> {

    protected BuildingController(BuildingRepository repository) {
        super(repository);
    }

    @GetMapping()
    public R<List<Building>> getByName(String name, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Building target = new Building();
        target.setName(name.trim());
        return R.ok(super.getByTarget(target, "name", fuzzy));
    }


}
