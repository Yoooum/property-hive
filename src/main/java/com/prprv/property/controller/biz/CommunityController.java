package com.prprv.property.controller.biz;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Community;
import com.prprv.property.repo.CommunityRepository;
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
@RequestMapping("/api/v1/community")
public class CommunityController extends AbstractCrudController<Community, CommunityRepository> {

    protected CommunityController(CommunityRepository repository) {
        super(repository);
    }

    /**
     * 根据小区名称查询小区信息
     * @param name 小区名称
     * @return R 小区信息
     */
    @GetMapping("/name/{name}")
    public R<Community> getByName(@PathVariable String name) {
        Community community = new Community();
        community.setName(name);
        Example<Community> example = Example.of(community);
        List<Community> communityList = this.repository.findAll(example);
        return communityList.isEmpty() ? R.error(E.NOT_FOUND) : R.ok(communityList.get(0));
    }
}
