package com.prprv.property.controller.biz;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Repair;
import com.prprv.property.repo.RepairRepository;
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
@RequestMapping("/api/v1/repair")
public class RepairController extends AbstractCrudController<Repair, RepairRepository> {

    protected RepairController(RepairRepository repository) {
        super(repository);
    }

    /**
     * 根据报修项目查询报修单信息
     *
     * @param name 报修项目
     * @return R 报修单信息
     */
     @GetMapping("/name/{name}")
        public R<List<Repair>> getByName(@PathVariable String name) {
            Repair repair = new Repair();
            repair.setName(name);
            Example<Repair> example = Example.of(repair);
            List<Repair> repairList = this.repository.findAll(example);
            return repairList.isEmpty() ? R.error(E.NOT_FOUND) : R.ok(repairList);
     }
}
