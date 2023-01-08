package com.laborete.LaboreteAPI.profile.services;

import com.laborete.LaboreteAPI.profile.entity.UserAvatarEntity;
import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import com.laborete.LaboreteAPI.profile.exception.ResourceBadRequestException;
import com.laborete.LaboreteAPI.profile.exception.ResourceFileUploadErrorException;
import com.laborete.LaboreteAPI.profile.exception.ResourceNotFoundException;
import com.laborete.LaboreteAPI.profile.mappers.UserMapper;
import com.laborete.LaboreteAPI.profile.models.CreateUserDTO;
import com.laborete.LaboreteAPI.profile.models.UserDTO;
import com.laborete.LaboreteAPI.profile.repository.UserAvatarRepository;
import com.laborete.LaboreteAPI.profile.repository.UsersRepository;
import com.laborete.LaboreteAPI.shared.common.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Objects;
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
    private static final String FILE_FAILED_UPLOAD = "Failed to upload file: ";
    private static final String ERROR_CREATING_DIRECTORY = "The directory was not created";
    private final UsersRepository usersRepository;
    private final UserAvatarRepository userAvatarRepository;
    private final UserMapper userMapper;
    @Value("${directory.path}")
    private String ROOT_PATH;

    public UsersServiceImpl(
            UsersRepository usersRepository,
            UserAvatarRepository userAvatarRepository,
            UserMapper userMapper
    ) {
        this.usersRepository = usersRepository;
        this.userAvatarRepository = userAvatarRepository;
        this.userMapper = userMapper;
    }

    public UserDTO getUserById(UUID id) {
        if (id == null) {
            throw new ResourceBadRequestException(UUID_IS_REQUIRED);
        }

        UserEntity user = this.usersRepository.getUserById(id).orElseThrow(
                () -> new ResourceNotFoundException(USER_NOT_FOUND + id)
        );;

        return this.userMapper.userEntityToUserDto(user);
    }

    public CreateUserDTO createUser(CreateUserDTO user) {
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

        return this.userMapper.createUserEntityToUserDto(
                this.usersRepository.save(userMapper.createUserDTOToUserEntity(user))
        );
    }

    @Transactional
    public ResponseEntity<HttpStatus> uploadUserAvatar(String name, MultipartFile file, UUID userId) {
        UserEntity user = this.usersRepository.getUserById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(USER_NOT_FOUND + userId)
                );

        if (file.isEmpty()) {
            throw new ResourceBadRequestException(FILE_IS_EMPTY);
        }
        if (name.isBlank() || name.isEmpty()) {
            throw new ResourceBadRequestException(FILE_NAME_IS_EMPTY);
        }

        try {
            byte[] bytes = file.getBytes();

            UUID uuid = UUID.randomUUID();
            var path = this.createDirectory(uuid);

            File avatar = new File(path, uuid + FileUtils.getFileExtension(file));
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(avatar));

            stream.write(bytes);
            stream.close();

            UserAvatarEntity userAvatarEntity = new UserAvatarEntity(uuid, name, file.getSize());
            user.setUserAvatarEntity(userAvatarEntity);
            this.userAvatarRepository.save(userAvatarEntity);
            this.usersRepository.save(user);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResourceFileUploadErrorException(FILE_FAILED_UPLOAD, e);
        }
    }

    private String createDirectory(UUID uuid) {
        try {
            File directory = new File(Objects.requireNonNull(ROOT_PATH));
            if (!directory.exists()) {
                Files.createDirectory(directory.toPath());
            }

            var  firstSymbol  = uuid.toString().charAt(0);
            File firstSymbolDirectory = new File(ROOT_PATH + firstSymbol);
            var  secondSymbol  = uuid.toString().charAt(1);
            File secondSymbolDirectory = new File(ROOT_PATH + firstSymbol + "\\" + secondSymbol);
            var  thirdSymbol  = uuid.toString().charAt(2);
            File thirdSymbolDirectory = new File(ROOT_PATH + firstSymbol + "\\" + secondSymbol + "\\" + thirdSymbol);

            if (!firstSymbolDirectory.exists()) {
                Files.createDirectory(firstSymbolDirectory.toPath());
            }
            if (!secondSymbolDirectory.exists()) {
                Files.createDirectory(secondSymbolDirectory.toPath());
            }
            if (!thirdSymbolDirectory.exists()) {
                Files.createDirectory(thirdSymbolDirectory.toPath());
            }

            return ROOT_PATH + firstSymbol + "\\" + secondSymbol + "\\" + thirdSymbol;
        } catch (Exception e) {
            throw new ResourceFileUploadErrorException(e.getMessage());
        }
    }
}
