package com.laborete.LaboreteAPI.services;

import com.laborete.LaboreteAPI.entity.Users;
import com.laborete.LaboreteAPI.exception.ResourceBadRequestException;
import com.laborete.LaboreteAPI.exception.ResourceNotFoundException;
import com.laborete.LaboreteAPI.exception.ResourceServerErrorException;
import com.laborete.LaboreteAPI.repository.UsersRepository;
import com.laborete.LaboreteAPI.constants.ConstantsHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {
    private static final String USER_NOT_FOUND = "User did not find with id:";
    private static final String FIRST_NAME_IS_REQUIRED = "The user name cannot be empty";
    private static final String SECOND_NAME_IS_REQUIRED = "The secondName cannot be empty";
    private static final String POSITION_IS_REQUIRED = "The position cannot be empty";
    private static final String LOCATION_IS_REQUIRED = "The location cannot be empty";
    private static final String UUID_IS_REQUIRED = "The UUID cannot be empty";
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getUserById(UUID uuid) {
        if (uuid == null) {
            throw new ResourceBadRequestException(UUID_IS_REQUIRED);
        }
        Users user = this.usersRepository.getUsersById(uuid);
        if (user == null) {
            throw new ResourceNotFoundException(USER_NOT_FOUND + uuid);
        }
        return user;
    }

    public Users createUser(Users user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new ResourceBadRequestException(FIRST_NAME_IS_REQUIRED);
        }
        if (user.getSecondName() == null || user.getSecondName().isEmpty()) {
            throw new ResourceBadRequestException(SECOND_NAME_IS_REQUIRED);
        }
        if (user.getLocation() == null || user.getLocation().isEmpty()) {
            throw new ResourceBadRequestException(LOCATION_IS_REQUIRED);
        }
        if (user.getPosition() == null || user.getPosition().isEmpty()) {
            throw new ResourceBadRequestException(POSITION_IS_REQUIRED);
        }

        return this.usersRepository.save(user);
    }

}
