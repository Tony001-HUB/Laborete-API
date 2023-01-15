package com.laborete.LaboreteAPI.profile.mappers;

import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import com.laborete.LaboreteAPI.profile.models.CreateUserDTO;
import com.laborete.LaboreteAPI.profile.models.UserDTO;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {
    UserEntity createUserDTOToUserEntity(CreateUserDTO user);
    UserDTO userEntityToUserDto(UserEntity user);

    UserEntity userDTOToUserEntity(UserDTO user);
}