package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.ParkingUse;
import com.prprv.property.repo.ParkingUseRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/parking/use")
public class ParkingUseController extends AbstractCrudController<ParkingUse, ParkingUseRepository> {

    protected ParkingUseController(ParkingUseRepository repository) {
        super(repository);
    }

}
