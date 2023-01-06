package com.laborete.LaboreteAPI.profile.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
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
