package com.slfl.portfolio_project.model;

public class ResponseData {
    private User user;
    private String jwt;

    public ResponseData(){
        super();
    }

    public ResponseData(User user, String jwt){
        this.user = user;
        this.jwt = jwt;
    }
    public ResponseData(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

}