package com.laborete.LaboreteAPI.profile.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
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
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("userAvatar")
    private ImageDTO userAvatar;
    @JsonProperty("userBackground")
    private ImageDTO userBackground;
}
