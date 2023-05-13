package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Building;
import com.prprv.property.repo.BuildingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/building")
public class BuildingController extends AbstractCrudController<Building, BuildingRepository> {

    protected BuildingController(BuildingRepository repository) {
        super(repository);
    }
}
