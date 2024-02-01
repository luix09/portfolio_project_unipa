package com.slfl.portfolio_project.model;

import java.util.ArrayList;

public class ResponseStatus {

    private String code;
    private String message;
    private String data;
    public ResponseStatus(){
        super();
    }

    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getData(){
        return this.data;
    }
    public void setData(String data){
        this.data = data;
    }

    public ResponseStatus(String code, String message, String data) {

        this.code = code;
        this.message = message;
        this.data = data;
    }


    public ResponseStatus emailAlreadyUsed(){
        return new ResponseStatus("401","Email già in uso","");
    }
    public ResponseStatus usernameAlreadyUsed(){
        return new ResponseStatus("401","Username già in uso","");
    }
    public ResponseStatus passwordLowSecurity(){
        return new ResponseStatus("401","Utilizza una password più complessa!","");
    }
    public ResponseStatus generalSuccess(){
        return new ResponseStatus("200","Operazione avvenuta con successo","");
    }
    public ResponseStatus loginSuccess(String jwt){
        return new ResponseStatus("200","Operazione avvenuta con successo",jwt);
    }
    public ResponseStatus generalError(Exception e) {
        return new ResponseStatus("404","Operazione fallita",e.getMessage());

    }

    public ResponseStatus userNotFound() {
        return new ResponseStatus("404","Utente non trovato","");
    }

    public ResponseStatus registrationSuccess(String username) {
        return new ResponseStatus("200","Utente registrato correttamente",username);

    }
}
