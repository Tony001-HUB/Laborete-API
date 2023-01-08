package com.laborete.LaboreteAPI.profile.services;

import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import com.laborete.LaboreteAPI.profile.models.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UsersService {
    UserEntity getUserById(UUID id);
    UserDTO createUser(UserDTO user);
    ResponseEntity<HttpStatus> uploadUserAvatar(String name, MultipartFile file, UUID userId);
}
