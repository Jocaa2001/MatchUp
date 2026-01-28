package com.matchup.user.controller;



import com.matchup.common.controller.CrudController;
import com.matchup.user.dto.UserDTO;
import com.matchup.user.entity.User;
import com.matchup.user.mapper.UserMapper;
import com.matchup.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController extends CrudController<User, UserDTO, UserService, UserMapper> {

    public UserController(UserService service, UserMapper mapper) {
        super(service, mapper);
    }


}
