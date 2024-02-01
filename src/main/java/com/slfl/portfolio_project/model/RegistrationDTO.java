package com.slfl.portfolio_project.model;

public class RegistrationDTO {
    private String username;
    private String password;
    private String email;

    public RegistrationDTO(){
        super();
    }

    public RegistrationDTO(String username, String email ,String password){
        super();
        this.username = username;
        this.password = password;
        this.email = email;
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
}