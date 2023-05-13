package com.prprv.property.controller.sys;

import com.prprv.property.controller.AbstractCrudController;
import com.prprv.property.entity.sys.User;
import com.prprv.property.repo.sys.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractCrudController<User, UserRepository> {

    protected UserController(UserRepository repository) {
        super(repository);
    }
}
