package com.laborete.LaboreteAPI.posts.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laborete.LaboreteAPI.profile.models.UserAvatarDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class PostDTO {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("date")
    private LocalDateTime creationDate = LocalDateTime.now();
    @JsonProperty("userId")
    private UUID userId;
    @JsonProperty("userFirstName")
    private String userFirstName;
    @JsonProperty("userSecondName")
    private String userSecondName;
    @JsonProperty("userPosition")
    private String userPosition;
    @JsonProperty("userAvatarEntity")
    private UserAvatarDTO userAvatarDTO;

}

