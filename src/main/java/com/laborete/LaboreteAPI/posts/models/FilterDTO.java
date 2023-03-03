package com.laborete.LaboreteAPI.posts.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDTO {
    @JsonProperty("searchValue")
    private String searchValue;

}
