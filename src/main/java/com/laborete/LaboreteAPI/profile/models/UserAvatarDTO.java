package com.laborete.LaboreteAPI.profile.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserAvatarDTO {
    private UUID id;
    private String name;

    public UserAvatarDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}