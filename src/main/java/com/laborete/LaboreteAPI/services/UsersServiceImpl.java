package com.laborete.LaboreteAPI.services;

import com.laborete.LaboreteAPI.entity.UserAvatarEntity;
import com.laborete.LaboreteAPI.entity.UserEntity;
import com.laborete.LaboreteAPI.exception.ResourceBadRequestException;
import com.laborete.LaboreteAPI.exception.ResourceFileUploadErrorException;
import com.laborete.LaboreteAPI.exception.ResourceNotFoundException;
import com.laborete.LaboreteAPI.repository.UserAvatarRepository;
import com.laborete.LaboreteAPI.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    private static final String USER_NOT_FOUND = "User was not found with id:";
    private static final String FIRST_NAME_IS_REQUIRED = "The user name cannot be empty";
    private static final String SECOND_NAME_IS_REQUIRED = "The secondName cannot be empty";
    private static final String POSITION_IS_REQUIRED = "The position cannot be empty";
    private static final String LOCATION_IS_REQUIRED = "The location cannot be empty";
    private static final String UUID_IS_REQUIRED = "The UUID cannot be empty";
    private static final String FILE_IS_EMPTY = "The file cannot be empty";
    private static final String FILE_NAME_IS_EMPTY = "The file name cannot be empty";
    private static final String FILE_FAILED_UPLOAD = "Failed to upload file";
    private static final String ERROR_CREATING_DIRECTORY = "The directory was not created";
    private final UsersRepository usersRepository;
    private final UserAvatarRepository userAvatarRepository;

    public UsersServiceImpl(UsersRepository usersRepository, UserAvatarRepository userAvatarRepository) {
        this.usersRepository = usersRepository;
        this.userAvatarRepository = userAvatarRepository;
    }

    public UserEntity getUserById(UUID id) {
        if (id == null) {
            throw new ResourceBadRequestException(UUID_IS_REQUIRED);
        }
        UserEntity user = this.usersRepository.getUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException(USER_NOT_FOUND + id);
        }
        return user;
    }

    public UserEntity createUser(UserEntity user) {
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

    public String uploadUserAvatar(String name, MultipartFile file, UUID userId) {
        UserEntity user = this.usersRepository.getUserById(userId);

        if (file.isEmpty()) {
            throw new ResourceBadRequestException(FILE_IS_EMPTY);
        }
        if (name.isBlank() || name.isEmpty()) {
            throw new ResourceBadRequestException(FILE_NAME_IS_EMPTY);
        }
        if (user == null) {
            throw new ResourceNotFoundException(USER_NOT_FOUND + userId);
        }

        try {
            byte[] bytes = file.getBytes();

            UUID uuid = UUID.randomUUID();
            String path = this.createDirectory(uuid);

            File avatar = new File(path, uuid + ".png");
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(avatar));

            stream.write(bytes);
            stream.close();

            UserAvatarEntity userAvatarEntity = new UserAvatarEntity(uuid, name, file.getSize());
            user.setUserAvatarEntity(userAvatarEntity);
            this.usersRepository.save(user);
            this.userAvatarRepository.save(userAvatarEntity);

            return "Avatar has been uploaded";
        } catch (Exception e) {
            throw new ResourceFileUploadErrorException(FILE_FAILED_UPLOAD);
        }
    }

    private String createDirectory(UUID uuid) {
        File directory = new File("D:\\usersAvatars");
        try {
            if (!directory.exists()) {boolean isCreated = directory.mkdir();}
            char firstSymbol  = uuid.toString().charAt(0);
            File firstSymbolDirectory = new File("D:\\usersAvatars\\" + firstSymbol);

            char secondSymbol  = uuid.toString().charAt(1);
            File secondSymbolDirectory = new File("D:\\usersAvatars\\" + firstSymbol + "\\" + secondSymbol);

            char thirdSymbol  = uuid.toString().charAt(2);
            File thirdSymbolDirectory = new File("D:\\usersAvatars\\" + firstSymbol + "\\" + secondSymbol + "\\" + thirdSymbol);

            if (!firstSymbolDirectory.exists()) {boolean isCreated = firstSymbolDirectory.mkdir();}
            if (!secondSymbolDirectory.exists()) {boolean isCreated = secondSymbolDirectory.mkdir();}
            if (!thirdSymbolDirectory.exists()) {boolean isCreated = thirdSymbolDirectory.mkdir();}

            return "D:\\usersAvatars\\" + firstSymbol + "\\" + secondSymbol + "\\" + thirdSymbol;
        } catch (Exception e) {
            throw new ResourceFileUploadErrorException(ERROR_CREATING_DIRECTORY);
        }
    }
}
