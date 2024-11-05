package com.laborete.LaboreteAPI.profile.mappers;

import com.laborete.LaboreteAPI.profile.entity.UserAvatarEntity;
import com.laborete.LaboreteAPI.profile.entity.UserBackgroundEntity;
import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import com.laborete.LaboreteAPI.profile.models.CreateUserDTO;
import com.laborete.LaboreteAPI.profile.models.ImageDTO;
import com.laborete.LaboreteAPI.profile.models.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {
    UserEntity createUserDTOToUserEntity(CreateUserDTO user);

    @Mapping(target = "userAvatar", source = "userAvatarEntity")
    @Mapping(target = "userBackground", source = "userBackgroundEntity")
    UserDTO userEntityToUserDto(UserEntity user);

    UserEntity userDTOToUserEntity(UserDTO user);

    ImageDTO userAvatarEntityToImageDTO(UserAvatarEntity user);

    ImageDTO userBackgroundEntityToImageDTO(UserBackgroundEntity user);

    List<UserDTO> userEntitiesListToUserDTOList(List<UserEntity> userEntities);
}