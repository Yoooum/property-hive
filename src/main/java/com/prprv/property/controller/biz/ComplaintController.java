package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Complaint;
import com.prprv.property.repo.ComplaintRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/complaint")
public class ComplaintController extends AbstractCrudController<Complaint, ComplaintRepository> {

    protected ComplaintController(ComplaintRepository repository) {
        super(repository);
    }

    @GetMapping()
    public R<List<Complaint>> getByTitle(String title, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Complaint target = new Complaint();
        try {
            target.setId(Long.parseLong(title));
        } catch (NumberFormatException e) {
            target.setTitle(title.trim());
        }
        return R.ok(super.getByTarget(target, "title", fuzzy));
    }
}
