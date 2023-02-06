package com.laborete.LaboreteAPI.profile.controller;

import com.laborete.LaboreteAPI.profile.models.CreateUserDTO;
import com.laborete.LaboreteAPI.profile.models.UserDTO;
import com.laborete.LaboreteAPI.profile.services.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController()
@RequestMapping("v1/users")
@Api(value = "Users", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Users"}, description = "Users Controller")
public class UsersController {
    private final UsersService usersService;

    public UsersController(
            UsersService usersService
    ) {
        this.usersService = usersService;
    }

    @ApiOperation(value = "Get user by id")
    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return new ResponseEntity<>(this.usersService.getUserById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create new user")
    @PostMapping()
    private ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO user) {
        return new ResponseEntity<>(
                usersService.createUser(user), HttpStatus.CREATED
        );
    }

    @ApiOperation(value = "Upload avatar to user")
    @RequestMapping(value = "/upload-avatar", method = RequestMethod.POST)
    private ResponseEntity<HttpStatus> uploadUserAvatar(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") UUID userId) {
        return this.usersService.uploadUserAvatar(name, file, userId);
    }


    @ApiOperation(value = "Upload background image to user")
    @RequestMapping(value = "/upload-background", method = RequestMethod.POST)
    private ResponseEntity<HttpStatus> uploadUserBackground(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") UUID userId) {
        return usersService.uploadUserBackground(name, file, userId);
    }

}
