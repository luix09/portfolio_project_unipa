package com.slfl.portfolio_project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String username;
    private String password;
    private String email;

    @JsonProperty("is_photographer")
    private boolean isPhotographer;

    public String toString(){
        return "Registration info: username: " + this.username + " password: " + this.password;
    }

    public boolean isPhotographer() {
        return isPhotographer;
    }
}