package com.laborete.LaboreteAPI.services;

import com.laborete.LaboreteAPI.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UsersService {
    UserEntity getUserById(UUID id);
    UserEntity createUser(UserEntity user);
    ResponseEntity<HttpStatus> uploadUserAvatar(String name, MultipartFile file, UUID userId);
}
