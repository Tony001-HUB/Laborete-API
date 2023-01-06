package com.laborete.LaboreteAPI.profile.mappers;

import com.laborete.LaboreteAPI.profile.entity.UserEntity;
import com.laborete.LaboreteAPI.profile.models.UserDTO;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    UserEntity userDTOToUserEntity(UserDTO user);
    UserDTO UserEntityToUserDto(UserEntity user);
}