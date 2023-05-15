package com.prprv.property.controller.biz;

import com.prprv.property.common.response.E;
import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Building;
import com.prprv.property.repo.BuildingRepository;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/building")
public class BuildingController extends AbstractCrudController<Building, BuildingRepository> {

    protected BuildingController(BuildingRepository repository) {
        super(repository);
    }

    /**
     * 根据楼栋名称查询楼栋信息
     *
     * @param name 楼栋名称
     * @return R 楼栋信息
     */
    @GetMapping("/name/{name}")
    public R<List<Building>> getByName(@PathVariable String name) {
        Building building = new Building();
        building.setName(name);
        Example<Building> example = Example.of(building);
        List<Building> buildingList = this.repository.findAll(example);
        return buildingList.isEmpty() ? R.error(E.NOT_FOUND) : R.ok(buildingList);
    }
}
