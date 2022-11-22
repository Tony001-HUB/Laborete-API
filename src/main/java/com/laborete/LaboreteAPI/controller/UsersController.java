package com.laborete.LaboreteAPI.controller;

import com.laborete.LaboreteAPI.entity.UserEntity;
import com.laborete.LaboreteAPI.modal.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("v1/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserEntity> getUserById(@PathVariable UUID id) {
        return new ResponseEntity<>(this.usersService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(this.usersService.createUser(userEntity), HttpStatus.CREATED);
    }
}
