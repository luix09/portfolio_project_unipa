package com.slfl.portfolio_project.controller;

import com.slfl.portfolio_project.model.LoginResponseDTO;
import com.slfl.portfolio_project.model.RegistrationDTO;
import com.slfl.portfolio_project.model.User;
import com.slfl.portfolio_project.model.ResponseStatus;
import com.slfl.portfolio_project.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseStatus registerUser(@RequestBody RegistrationDTO body){

            ResponseStatus resultCheck = authenticationService.checkExistUser(body.getUsername(), body.getEmail());
            if( resultCheck.getCode() != "200"){
                return resultCheck;
            }
            resultCheck = authenticationService.registerUser(body.getUsername(), body.getEmail(), body.getPassword());

            return resultCheck;

    }

    @PostMapping("/login")
    public ResponseStatus loginUser(@RequestBody RegistrationDTO body){
        ResponseStatus resultCheck = authenticationService.checkExistUserLogin(body.getUsername());
        if( resultCheck.getCode() != "200"){
            return resultCheck;
        }
        resultCheck = authenticationService.loginUser(body.getUsername(), body.getPassword());
        return resultCheck;
    }
}