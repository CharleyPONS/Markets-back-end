package com.info.markets.controller;

import com.info.markets.model.UserEntity;
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
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public List<UserEntity> retrieveAllUsers(){
        return this.userServiceImpl.findAllUser();

    }

    @GetMapping("/{id}")
    public Optional<UserEntity> retrieveUser(@PathVariable("id") int id){
        return this.userServiceImpl.findUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody UserEntity userEntity){
        this.userServiceImpl.saveUser(userEntity);
    }


}
