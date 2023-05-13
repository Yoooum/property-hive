package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Complaint;
import com.prprv.property.repo.ComplaintRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/complaint")
public class ComplaintController extends AbstractCrudController<Complaint, ComplaintRepository> {

    protected ComplaintController(ComplaintRepository repository) {
        super(repository);
    }
}
