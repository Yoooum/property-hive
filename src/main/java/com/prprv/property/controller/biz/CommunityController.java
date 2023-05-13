package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Community;
import com.prprv.property.repo.CommunityRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/community")
public class CommunityController extends AbstractCrudController<Community, CommunityRepository> {

    protected CommunityController(CommunityRepository repository) {
        super(repository);
    }
}
