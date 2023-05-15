package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Property;
import com.prprv.property.repo.PropertyRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/property")
public class PropertyController extends AbstractCrudController<Property, PropertyRepository> {

    protected PropertyController(PropertyRepository repository) {
        super(repository);
    }

}
