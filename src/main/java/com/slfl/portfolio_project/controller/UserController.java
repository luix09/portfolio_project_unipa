package com.slfl.portfolio_project.controller;

import com.nimbusds.jwt.JWT;
import com.slfl.portfolio_project.configuration.SecurityConfiguration;
import com.slfl.portfolio_project.model.ResponseStatus;
import com.slfl.portfolio_project.model.User;
import com.slfl.portfolio_project.repository.UserRepository;
import com.slfl.portfolio_project.service.TokenService;
import com.slfl.portfolio_project.utils.RSAKeyProperties;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private final TokenService tokenService = new TokenService();
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/checkUser")
    public ResponseStatus getInfo(){
        return  new ResponseStatus().checkSuccess();
    }
    @GetMapping("/profile")
    public ResponseStatus getProfile(@RequestHeader (HttpHeaders.AUTHORIZATION) String token) throws UnsupportedEncodingException, JSONException {
        JSONObject decodedToken = tokenService.decodeJwt(token);
        String info = decodedToken.getString("sub");
        User user = userRepository.findByUsername(info).get();
        return new ResponseStatus().profileSuccess(user);

    }


}