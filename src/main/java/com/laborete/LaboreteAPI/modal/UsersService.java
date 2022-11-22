package com.laborete.LaboreteAPI.modal;

import com.laborete.LaboreteAPI.entity.UserEntity;

import java.util.UUID;

public interface UsersService {
    UserEntity getUserById(UUID id);
    UserEntity createUser(UserEntity user);
}
