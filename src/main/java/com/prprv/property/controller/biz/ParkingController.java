package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Parking;
import com.prprv.property.repo.ParkingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/parking")
public class ParkingController extends AbstractCrudController<Parking, ParkingRepository> {

    protected ParkingController(ParkingRepository repository) {
        super(repository);
    }
}
