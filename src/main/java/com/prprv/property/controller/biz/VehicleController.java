package com.prprv.property.controller.biz;

import com.prprv.property.common.response.R;
import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.biz.Vehicle;
import com.prprv.property.repo.VehicleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController extends AbstractCrudController<Vehicle, VehicleRepository> {

    protected VehicleController(VehicleRepository repository) {
        super(repository);
    }
    @GetMapping()
    public R<List<Vehicle>> getByLicensePlate(String licensePlate, @RequestParam(defaultValue = "false") Boolean fuzzy) {
        Vehicle target = new Vehicle();
        target.setLicensePlate(licensePlate.trim());
        return R.ok(super.getByTarget(target, "licensePlate", fuzzy));
    }
}
