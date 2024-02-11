package com.slfl.portfolio_project.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserDTORequest {
    public UpdateUserDTORequest() {}

    public UpdateUserDTORequest(String name, String surname, String username, String password, boolean isPhotographer) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.isPhotographer = isPhotographer;
    }

    @JsonProperty
    private String name;

    @JsonProperty
    private String surname;

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String email;

    @JsonProperty
    private boolean isPhotographer;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isPhotographer() {
        return isPhotographer;
    }

    public void setPhotographer(boolean photographer) {
        isPhotographer = photographer;
    }
}
