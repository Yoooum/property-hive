package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Repair;
import com.prprv.property.entity.sys.User;
import com.prprv.property.repo.RepairRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping()
    public R<List<Repair>> getByName(String name, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Repair target = new Repair();
        User user = new User();
        user.setUsername(name.trim());
        target.setUser(user);
        return R.ok(super.getByTarget(target, "user.username", fuzzy));
    }

//    @GetMapping
//    public R<List<Repair>> getByIdLikeOrNameLike(Long id, String name) {
//        return R.ok(super.repository.findByIdLikeOrNameLike(id, name));
//    }
}
