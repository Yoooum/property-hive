package com.prprv.property.controller.biz;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Owner;
import com.prprv.property.repo.OwnerRepository;
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
@RequestMapping("/api/v1/owner")
public class OwnerController extends AbstractCrudController<Owner, OwnerRepository> {

    protected OwnerController(OwnerRepository repository) {
        super(repository);
    }

    /**
     * 根据业主名称查询业主信息
     *
     * @param name 业主名称
     * @return R 业主信息
     */
    @GetMapping("/name/{name}")
    public R<List<Owner>> getByName(@PathVariable String name) {
        Owner owner = new Owner();
        owner.setName(name);
        Example<Owner> example = Example.of(owner);
        List<Owner> ownerList = this.repository.findAll(example);
        return ownerList.isEmpty() ? R.error(E.NOT_FOUND) : R.ok(ownerList);
    }

}
