package com.laborete.LaboreteAPI.profile.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserDTO {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("secondName")
    private String secondName;
    @JsonProperty("position")
    private String position;
    @JsonProperty("location")
    private String location;
    @JsonProperty("generalInfo")
    private String generalInfo;
}
