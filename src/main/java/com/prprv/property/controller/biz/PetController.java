package com.prprv.property.controller.biz;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Pet;
import com.prprv.property.repo.PetRepository;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/pet")
public class PetController extends AbstractCrudController<Pet, PetRepository> {

    protected PetController(PetRepository repository) {
        super(repository);
    }

    /**
     * 根据宠物名称查询宠物信息
     *
     * @param name 宠物名称
     * @return R 宠物信息
     */
     @GetMapping("/name/{name}")
        public R<List<Pet>> getByName(@PathVariable String name) {
            Pet pet = new Pet();
            pet.setName(name);
            Example<Pet> example = Example.of(pet);
            List<Pet> petList = this.repository.findAll(example);
            return petList.isEmpty() ? R.error(E.NOT_FOUND) : R.ok(petList);
     }
}
