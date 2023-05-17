package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Activity;
import com.prprv.property.repo.ActivityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/activity")
public class ActivityController extends AbstractCrudController<Activity, ActivityRepository> {

    protected ActivityController(ActivityRepository repository) {
        super(repository);
    }

    @GetMapping()
    public R<List<Activity>> getByName(String title, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Activity target = new Activity();
        try {
            target.setId(Long.parseLong(title));
        } catch (NumberFormatException e) {
            target.setTitle(title.trim());
        }
        return R.ok(super.getByTarget(target, "title", fuzzy));
    }
}
