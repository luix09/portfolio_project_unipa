package com.slfl.portfolio_project.model;

public class ResponseStatus {

    private String code;
    private String message;
    private ResponseData data;
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
    public ResponseData getData(){
        return this.data;
    }
    public void setData(ResponseData data){
        this.data = data;
    }
    public ResponseStatus(String code, String message, ResponseData data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ResponseStatus emailAlreadyUsed(){
        return new ResponseStatus("401","Email già in uso",new ResponseData());
    }
    public ResponseStatus usernameAlreadyUsed(){
        return new ResponseStatus("401","Username già in uso",new ResponseData());
    }
    public ResponseStatus passwordLowSecurity(){
        return new ResponseStatus("401","Utilizza una password più complessa!",new ResponseData());
    }
    public ResponseStatus generalSuccess(){
        return new ResponseStatus("200","Operazione avvenuta con successo",new ResponseData());
    }
    public ResponseStatus loginSuccess(ResponseData loginResponseDTO){
        return new ResponseStatus("200","Operazione avvenuta con successo",loginResponseDTO);
    }
    public ResponseStatus generalError(Exception e) {
        return new ResponseStatus("404","Operazione fallita",new ResponseData());
    }
    public ResponseStatus userNotFound() {
        return new ResponseStatus("404","Utente non trovato",new ResponseData());
    }
    public ResponseStatus registrationSuccess(User user) {
        return new ResponseStatus("200","Utente registrato correttamente",new ResponseData(user));
    }
    public ResponseStatus checkSuccess() {
        return new ResponseStatus("200","Utente autorizzato",new ResponseData());
    }
    public ResponseStatus profileSuccess(User user) {
        return new ResponseStatus("200","Profilo utente",new ResponseData(user));
    }

    public ResponseStatus updatedUserSuccessfully() {
        return new ResponseStatus("200","Utente modificato correttamente",new ResponseData());
    }

    public ResponseStatus sessionNotAuthenticated() {
        return new ResponseStatus("200","Non sei autorizzato ad accedere a questa risorsa.", new ResponseData());
    }
}
