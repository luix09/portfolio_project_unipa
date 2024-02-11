package com.slfl.portfolio_project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationDTO {
    private String username;
    private String password;
    private String email;

    @JsonProperty("is_photographer")
    private boolean isPhotographer;

    public RegistrationDTO(){
        super();
    }

    public RegistrationDTO(String username, String email, String password, boolean isPhotographer) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.isPhotographer = isPhotographer;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String toString(){
        return "Registration info: username: " + this.username + " password: " + this.password;
    }

    public boolean isPhotographer() {
        return isPhotographer;
    }

    public void setPhotographer(boolean photographer) {
        isPhotographer = photographer;
    }
}