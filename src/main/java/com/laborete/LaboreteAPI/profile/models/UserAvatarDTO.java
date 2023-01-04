package com.laborete.LaboreteAPI.profile.models;

import java.util.UUID;

public class UserAvatarDTO {
    private UUID id;
    private String name;

    public UserAvatarDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}