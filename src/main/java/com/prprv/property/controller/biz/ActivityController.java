package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Activity;
import com.prprv.property.repo.ActivityRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/activity")
public class ActivityController extends AbstractCrudController<Activity, ActivityRepository> {

    protected ActivityController(ActivityRepository repository) {
        super(repository);
    }
}
