package com.slfl.portfolio_project.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseStatus<T> {

    @Setter
    @Getter
    private String code;
    private String message;
    private ResponseData<T> data;
    public ResponseStatus(){
        super();
    }
    public ResponseStatus(String code, String message, ResponseData<T> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ResponseStatus<T> emailAlreadyUsed(){
        return new ResponseStatus<T>("401","Email già in uso",new ResponseData());
    }
    public ResponseStatus<T> usernameAlreadyUsed(){
        return new ResponseStatus<T>("401","Username già in uso",new ResponseData());
    }
    public ResponseStatus<T> passwordLowSecurity(){
        return new ResponseStatus<T>("401","Utilizza una password più complessa!",new ResponseData());
    }
    public ResponseStatus<T> generalSuccess(){
        return new ResponseStatus<T>("200","Operazione avvenuta con successo",new ResponseData());
    }
    public ResponseStatus<T> loginSuccess(ResponseData loginResponseDTO){
        return new ResponseStatus<T>("200","Operazione avvenuta con successo",loginResponseDTO);
    }
    public ResponseStatus<T> generalError(Exception e) {
        return new ResponseStatus<T>("404", "Operazione fallita: " + e, new ResponseData());
    }

    public ResponseStatus<T> invalidCredentials(Exception e) {
        return new ResponseStatus<T>("404", e.getMessage(), new ResponseData());
    }

    public ResponseStatus<T> userNotFound() {
        return new ResponseStatus<T>("404","Utente non trovato",new ResponseData());
    }
    public ResponseStatus<User> registrationSuccess(User user) {
        return new ResponseStatus<>("200","Utente registrato correttamente",new ResponseData<>(user));
    }
    public ResponseStatus<T> checkSuccess() {
        return new ResponseStatus<T>("200","Utente autorizzato",new ResponseData());
    }
    public ResponseStatus<T> profileSuccess(User user) {
        return new ResponseStatus<T>("200","Profilo utente",new ResponseData(user));
    }

    public ResponseStatus<T> updatedUserSuccessfully() {
        return new ResponseStatus<T>("200","Utente modificato correttamente",new ResponseData());
    }

    public ResponseStatus<T> sessionNotAuthenticated() {
        return new ResponseStatus<T>("401","Non sei autorizzato ad accedere a questa risorsa.", new ResponseData());
    }

    public ResponseStatus<T> invalidPassword() {
        return new ResponseStatus<T>("404","Password non corretta, riprova.", new ResponseData<>());
    }

    public ResponseStatus<List<Album>> fetchedAllAlbums(List<Album> albums) {
        return new ResponseStatus<>("200", "Album selezionati correttamente", new ResponseData<>(albums));
    }
}
