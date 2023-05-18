package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Community;
import com.prprv.property.entity.biz.Visitor;
import com.prprv.property.repo.VisitorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */

@RestController
@RequestMapping("/api/v1/visitor")
public class VisitorController extends AbstractCrudController<Visitor, VisitorRepository> {

    protected VisitorController(VisitorRepository repository) {
        super(repository);
    }

    /**
     * 根据访客名称查询访客信息
     *
     * @param name 访客名称
     * @return R 访客信息
     */
     @GetMapping()
    public R<List<Visitor>> getByName(String name, @RequestParam(defaultValue = "false") Boolean fuzzy) {
         Visitor visitor = new Visitor();
         visitor.setName(name.trim());
         Community community = new Community();
         community.setName(name.trim());
         //如果是数字则按照ID 访问时间查询
         if (name.matches("\\d+")) {
             visitor.setId(Long.parseLong(name));
             visitor.setVisitTime(name);
         }
         visitor.setCommunity(community);
         String[] properties = {"name", "id", "community.name","visitTime"};
         List<Visitor> visitorList = super.getByTarget(visitor,properties,fuzzy);
         return R.ok(visitorList);
    }
}
