package com.prprv.property.controller.biz.parallel;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.parallel.ParkingInfoV2;
import com.prprv.property.repo.parallel.ParkingInfoV2Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/parallel/parking/info")
public class ParkingInfoV2Controller extends AbstractCrudController<ParkingInfoV2, ParkingInfoV2Repository> {
    protected ParkingInfoV2Controller(ParkingInfoV2Repository repository) {
        super(repository);
    }
    @GetMapping("/name")
    public List<ParkingInfoV2> findByParkName(String name) {
        ParkingInfoV2 parkingInfoV2 = new ParkingInfoV2();
        parkingInfoV2.setName(name);
        return super.getByTarget(parkingInfoV2,"name",false);
    }

    @GetMapping("/community")
    public List<ParkingInfoV2> findByCommunity(String community) {
        ParkingInfoV2 parkingTypeV2 = new ParkingInfoV2();
        parkingTypeV2.setCommunity(community);
        return super.getByTarget(parkingTypeV2,"community",false);
    }
}
