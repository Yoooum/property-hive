package com.prprv.property.controller.biz;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Vehicle;
import com.prprv.property.repo.VehicleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController extends AbstractCrudController<Vehicle, VehicleRepository> {

    protected VehicleController(VehicleRepository repository) {
        super(repository);
    }
}
