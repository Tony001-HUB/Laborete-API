package com.laborete.LaboreteAPI.services;

import com.laborete.LaboreteAPI.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UsersService {
    UserEntity getUserById(UUID id);
    UserEntity createUser(UserEntity user);
    String uploadUserAvatar(String name, MultipartFile file);
}
