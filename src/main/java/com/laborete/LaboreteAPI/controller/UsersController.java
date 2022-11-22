package com.laborete.LaboreteAPI.controller;

import com.laborete.LaboreteAPI.entity.Users;
import com.laborete.LaboreteAPI.services.UsersService;
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

    @GetMapping("/{uuid}")
    private ResponseEntity<Users> getUserById(@PathVariable UUID uuid) {
        return new ResponseEntity<>(this.usersService.getUserById(uuid), HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Users> createUser(@RequestBody Users users) {
        return new ResponseEntity<>(this.usersService.createUser(users), HttpStatus.CREATED);
    }
}
