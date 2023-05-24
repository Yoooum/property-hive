package com.prprv.property.controller.biz.parallel;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.parallel.ParkingTypeV2;
import com.prprv.property.repo.parallel.ParkingTypeV2Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/parallel/parking/type")
public class ParkingTypeV2Controller extends AbstractCrudController<ParkingTypeV2, ParkingTypeV2Repository> {
    protected ParkingTypeV2Controller(ParkingTypeV2Repository repository) {
        super(repository);
    }

    @GetMapping("/name")
    public List<ParkingTypeV2> findByParkName(String parkName) {
        ParkingTypeV2 parkingTypeV2 = new ParkingTypeV2();
        parkingTypeV2.setParkName(parkName);
        return super.getByTarget(parkingTypeV2,"parkName",false);
    }

    @GetMapping("/community")
    public List<ParkingTypeV2> findByCommunity(String community) {
        ParkingTypeV2 parkingTypeV2 = new ParkingTypeV2();
        parkingTypeV2.setCommunity(community);
        return super.getByTarget(parkingTypeV2,"community",false);
    }

}
