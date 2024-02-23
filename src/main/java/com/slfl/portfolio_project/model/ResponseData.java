package com.slfl.portfolio_project.model;

import lombok.Setter;

@Setter
public class ResponseData<T> {
    private T user;
    @Setter
    private String jwt;

    public ResponseData(){
        super();
    }

    public ResponseData(T user, String jwt){
        this.user = (T) user;
        this.jwt = jwt;
    }
    public ResponseData(T user){
        this.user = user;
    }

    public T getUser(){
        return this.user;
    }

    public void setUser(T user){
        this.user = user;
    }

    public String getJwt(){
        return this.jwt;
    }

}