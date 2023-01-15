package com.laborete.LaboreteAPI.posts.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreatePostDTO {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("creationTime")
    private LocalDateTime creationDate;
    @JsonProperty("user_id")
    private UUID userId;

}

