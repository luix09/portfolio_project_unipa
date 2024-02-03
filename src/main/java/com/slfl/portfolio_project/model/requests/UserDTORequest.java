package com.slfl.portfolio_project.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTORequest {
    public UserDTORequest() {}

    public UserDTORequest(String username, String password, String email) {}

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
