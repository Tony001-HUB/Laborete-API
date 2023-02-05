package com.laborete.LaboreteAPI.profile.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserBackgroundDTO {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("size")
    private Long size;
    @JsonProperty("extension")
    private String extension;
}
