package com.laborete.LaboreteAPI.posts.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreatePostDTO {

    @JsonProperty("text")
    private String text;
    @JsonProperty("user-id")
    private UUID userId;

}

