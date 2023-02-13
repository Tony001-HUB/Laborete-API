package com.laborete.LaboreteAPI.profile.services;

import com.laborete.LaboreteAPI.exception.ResourceBadRequestException;
import com.laborete.LaboreteAPI.exception.ResourceFileUploadErrorException;
import com.laborete.LaboreteAPI.exception.ResourceNotFoundException;
import com.laborete.LaboreteAPI.profile.entity.UserAvatarEntity;
import com.laborete.LaboreteAPI.profile.entity.UserBackgroundEntity;
import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import com.laborete.LaboreteAPI.profile.mappers.UserMapper;
import com.laborete.LaboreteAPI.profile.models.CreateUserDTO;
import com.laborete.LaboreteAPI.profile.models.UserDTO;
import com.laborete.LaboreteAPI.profile.repository.UserAvatarRepository;
import com.laborete.LaboreteAPI.profile.repository.UserBackgroundRepository;
import com.laborete.LaboreteAPI.profile.repository.UsersRepository;
import com.laborete.LaboreteAPI.shared.common.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
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
    private static final String FAILED_TO_EXTRACT_BYTES = "Failed to extract bytes";
    private static final String AVATAR_NOT_FOUND = "Avatar was not found";
    private static final String BACKGROUND_NOT_FOUND = "Background was not found";
    private static final String DIRECTORY_NOT_FOUND = "Directory was not found";
    private static final String ERROR_CREATING_DIRECTORY = "The directory was not created";

    private final UsersRepository usersRepository;
    private final UserAvatarRepository userAvatarRepository;
    private final UserBackgroundRepository userBackgroundRepository;
    private final UserMapper userMapper;

    @Value("${directory.path.avatars}")
    private String ROOT_PATH_AVATARS;
    @Value("${directory.path.background}")
    private String ROOT_PATH_BACKGROUND;

    public UsersServiceImpl(
            UsersRepository usersRepository,
            UserAvatarRepository userAvatarRepository,
            UserMapper userMapper,
            UserBackgroundRepository userBackgroundRepository) {
        this.usersRepository = usersRepository;
        this.userAvatarRepository = userAvatarRepository;
        this.userMapper = userMapper;
        this.userBackgroundRepository = userBackgroundRepository;
    }

    public UserDTO getUserById(UUID id) {
        if (id == null) {
            throw new ResourceBadRequestException(UUID_IS_REQUIRED);
        }
        UserEntity user = this.usersRepository.getUserById(id).orElseThrow(
                () -> new ResourceNotFoundException(USER_NOT_FOUND + id)
        );
        var userDTO = userMapper.userEntityToUserDto(user);
        if (userDTO.getUserAvatar() != null) {
            UUID avatarId = userDTO.getUserAvatar().getId();
            userDTO.getUserAvatar()
                    .setBase64(
                            Base64.getEncoder()
                                    .encodeToString(
                                            getBytesOfAvatar(avatarId)));
        }
        if (userDTO.getUserBackground() != null) {
            UUID backgroundId = userDTO.getUserBackground().getId();
            userDTO.getUserBackground()
                    .setBase64(
                            Base64.getEncoder()
                                    .encodeToString(
                                            getBytesOfBackground(backgroundId)));
        }
        return userDTO;
    }

    public UserDTO createUser(CreateUserDTO user) {
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

        return this.userMapper.userEntityToUserDto(
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
            var path = createDirectory(uuid, ROOT_PATH_AVATARS);
            var fileTypeMap = new MimetypesFileTypeMap();

            File avatar = new File(path, uuid + FileUtils.getFileExtension(file));
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(avatar));

            stream.write(bytes);
            stream.close();


            UserAvatarEntity userAvatarEntity = new UserAvatarEntity(
                    uuid,
                    name,
                    file.getSize(),
                    FileUtils.getFileExtension(file),
                    fileTypeMap.getContentType(avatar));
            user.setUserAvatarEntity(userAvatarEntity);
            this.userAvatarRepository.save(userAvatarEntity);
            this.usersRepository.save(user);
            usersRepository.flush();


            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResourceFileUploadErrorException(FILE_FAILED_UPLOAD, e);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> uploadUserBackground(String name, MultipartFile file, UUID userId) {
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
            var path = createDirectory(uuid, ROOT_PATH_BACKGROUND);
            var fileTypeMap = new MimetypesFileTypeMap();

            File background = new File(path, uuid + FileUtils.getFileExtension(file));
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(background));

            stream.write(bytes);
            stream.close();

            UserBackgroundEntity userBackgroundEntity = new UserBackgroundEntity(
                    uuid,
                    name,
                    file.getSize(),
                    FileUtils.getFileExtension(file),
                    fileTypeMap.getContentType(background));

            user.setUserBackgroundEntity(userBackgroundEntity);
            userBackgroundRepository.save(userBackgroundEntity);
            usersRepository.save(user);
            usersRepository.flush();


            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ResourceFileUploadErrorException(FILE_FAILED_UPLOAD, e);
        }
    }

    private byte[] getBytesOfAvatar(UUID avatarId) {
        var userAvatarEntity = userAvatarRepository.getUserAvatarById(
                avatarId).orElseThrow(
                () -> new ResourceNotFoundException(AVATAR_NOT_FOUND + avatarId)
        );
        var pathVar = getDirectory(avatarId, ROOT_PATH_AVATARS) + "\\" + avatarId + userAvatarEntity.getExtension();
        byte[] bytes;

        try {
            bytes = Files.readAllBytes(Path.of(pathVar));
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_EXTRACT_BYTES, e);
        }
        return bytes;
    }

    private byte[] getBytesOfBackground(UUID backgroundId) {
        var userBackgroundEntity = userBackgroundRepository.getUserBackgroundById(
                backgroundId).orElseThrow(
                () -> new ResourceNotFoundException(BACKGROUND_NOT_FOUND + backgroundId)
        );
        var pathVar = getDirectory(backgroundId, ROOT_PATH_BACKGROUND) + "\\" + backgroundId + userBackgroundEntity.getExtension();
        byte[] bytes;

        try {
            bytes = Files.readAllBytes(Path.of(pathVar));
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_EXTRACT_BYTES, e);
        }
        return bytes;
    }


    private String createDirectory(UUID imageId, String parentDirectory) {
        try {
            File directory = new File(Objects.requireNonNull(parentDirectory));
            if (!directory.exists()) {
                Files.createDirectory(directory.toPath());
            }

            var firstSymbol = imageId.toString().charAt(0);
            File firstSymbolDirectory = new File(parentDirectory + firstSymbol);
            var secondSymbol = imageId.toString().charAt(1);
            File secondSymbolDirectory = new File(parentDirectory + firstSymbol + "\\" + secondSymbol);
            var thirdSymbol = imageId.toString().charAt(2);
            File thirdSymbolDirectory = new File(parentDirectory + firstSymbol + "\\" + secondSymbol + "\\" + thirdSymbol);

            if (!firstSymbolDirectory.exists()) {
                Files.createDirectory(firstSymbolDirectory.toPath());
            }
            if (!secondSymbolDirectory.exists()) {
                Files.createDirectory(secondSymbolDirectory.toPath());
            }
            if (!thirdSymbolDirectory.exists()) {
                Files.createDirectory(thirdSymbolDirectory.toPath());
            }

            return parentDirectory + firstSymbol + "\\" + secondSymbol + "\\" + thirdSymbol;
        } catch (Exception e) {
            throw new ResourceFileUploadErrorException(ERROR_CREATING_DIRECTORY + e.getMessage());
        }

    }

    private String getDirectory(UUID imageId, String parentDirectory) {
        try {
            var firstSymbol = imageId.toString().charAt(0);
            var secondSymbol = imageId.toString().charAt(1);
            var thirdSymbol = imageId.toString().charAt(2);

            return parentDirectory + firstSymbol + "\\" + secondSymbol + "\\" + thirdSymbol;
        } catch (Exception e) {
            throw new ResourceNotFoundException(DIRECTORY_NOT_FOUND + e.getMessage());
        }
    }


}
