package com.laborete.LaboreteAPI.profile.services;

import com.laborete.LaboreteAPI.profile.models.CreateUserDTO;
import com.laborete.LaboreteAPI.profile.models.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    UserDTO getUserById(UUID id);
    UserDTO createUser(CreateUserDTO user);
    ResponseEntity<HttpStatus> uploadUserAvatar(String name, MultipartFile file, UUID userId);
    ResponseEntity<HttpStatus> uploadUserBackground(String name, MultipartFile file, UUID userId);
    List<UserDTO> filterUsers(String rsqlFilter);
}
