package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Pet;
import com.prprv.property.repo.PetRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/pet")
public class PetController extends AbstractCrudController<Pet, PetRepository> {

    protected PetController(PetRepository repository) {
        super(repository);
    }
}
