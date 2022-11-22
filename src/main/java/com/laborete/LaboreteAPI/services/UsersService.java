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
    public static final String userNotFound = "User did not find with id:";
    public static final String firstNameIsRequired = "The user name cannot be empty";
    public static final String secondNameIsRequired = "The secondName cannot be empty";
    public static final String positionIsRequired = "The position cannot be empty";
    public static final String locationIsRequired = "The location cannot be empty";
    public static final String uuidIsRequired = "The UUID cannot be empty";
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getUserById(UUID uuid) {
        if (uuid == null) {
            throw new ResourceBadRequestException(uuidIsRequired);
        }
        Users user = this.usersRepository.getUsersById(uuid);
        if (user == null) {
            throw new ResourceNotFoundException(userNotFound + uuid);
        }
        return user;
    }

    public Users createUser(Users user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new ResourceBadRequestException(firstNameIsRequired);
        }
        if (user.getSecondName() == null || user.getSecondName().isEmpty()) {
            throw new ResourceBadRequestException(secondNameIsRequired);
        }
        if (user.getLocation() == null || user.getLocation().isEmpty()) {
            throw new ResourceBadRequestException(locationIsRequired);
        }
        if (user.getPosition() == null || user.getPosition().isEmpty()) {
            throw new ResourceBadRequestException(positionIsRequired);
        }

        return this.usersRepository.save(user);
    }

}
