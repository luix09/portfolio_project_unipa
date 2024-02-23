package com.slfl.portfolio_project.model.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class PictureCreateDTO {
    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty
    private String category;

    @JsonProperty
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date date;
}
