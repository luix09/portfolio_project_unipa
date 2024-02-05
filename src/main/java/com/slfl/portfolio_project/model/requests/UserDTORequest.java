package com.slfl.portfolio_project.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTORequest {
    public UserDTORequest(){
        super();
    }
    public UserDTORequest(String name, String surname, String username, String password ) {
        super();
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    private String name;
    private String surname;
    private String username;
    private String password;



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
}
