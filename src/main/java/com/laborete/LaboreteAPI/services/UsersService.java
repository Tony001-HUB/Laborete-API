package com.laborete.LaboreteAPI.services;

import com.laborete.LaboreteAPI.entity.Users;
import com.laborete.LaboreteAPI.exception.ResourceBadRequestException;
import com.laborete.LaboreteAPI.exception.ResourceServerErrorException;
import com.laborete.LaboreteAPI.repository.UsersRepository;
import com.laborete.LaboreteAPI.constants.ConstantsHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getUserById(UUID uuid) {
        try {
            if (uuid == null) {
                throw new ResourceBadRequestException(ConstantsHelper.uuidIsRequired);
            }
            return this.usersRepository.getUsersById(uuid);
        } catch (Exception e) {
            throw new ResourceServerErrorException(ConstantsHelper.serverUnavailable);
        }
    }

    public Users createUser(Users user) {
        try {
            if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
                throw new ResourceBadRequestException(ConstantsHelper.firstNameIsRequired);
            }
            if (user.getSecondName() == null || user.getSecondName().isEmpty()) {
                throw new ResourceBadRequestException(ConstantsHelper.secondNameIsRequired);
            }
            if (user.getLocation() == null || user.getLocation().isEmpty()) {
                throw new ResourceBadRequestException(ConstantsHelper.locationIsRequired);
            }
            if (user.getPosition() == null || user.getPosition().isEmpty()) {
                throw new ResourceBadRequestException(ConstantsHelper.positionIsRequired);
            }

            return this.usersRepository.save(user);
        } catch (Exception e) {
            throw new ResourceServerErrorException(ConstantsHelper.serverUnavailable);
        }
    }

}
