package com.slfl.portfolio_project.controller;

import com.slfl.portfolio_project.model.ResponseStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @GetMapping("/checkUser")
    public ResponseStatus getInfo(){
        return  new ResponseStatus().checkSuccess();
    }

}