package com.info.markets.controller;

import com.info.markets.model.UserEntity;
import com.info.markets.sevice.UserService;
import com.info.markets.sevice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> retrieveAllUsers(){
        return this.userService.findAllUser();

    }

    @GetMapping("/{id}")
    public Optional<UserEntity> retrieveUser(@PathVariable("id") int id) throws Exception {
        return this.userService.findUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody UserEntity userEntity){
        this.userService.saveUser(userEntity);
    }


}
