package com.slfl.portfolio_project.controller;

import com.slfl.portfolio_project.model.ResponseStatus;
import com.slfl.portfolio_project.model.User;
import com.slfl.portfolio_project.model.requests.UserDTORequest;
import com.slfl.portfolio_project.repository.UserRepository;
import com.slfl.portfolio_project.service.TokenService;
import com.slfl.portfolio_project.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {
    private final TokenService tokenService = new TokenService();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/checkUser")
    public ResponseStatus getInfo() {
        return new ResponseStatus().checkSuccess();
    }

    @GetMapping("/profile")
    public ResponseStatus getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws UnsupportedEncodingException, JSONException {
        try {
            JSONObject decodedToken = tokenService.decodeJwt(token);
            String info = decodedToken.getString("sub");
            User user = userRepository.findByUsername(info).get();
            return new ResponseStatus().profileSuccess(user);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", ex);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseStatus updateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable int id, @RequestBody UserDTORequest updatedUser) throws UnsupportedEncodingException, JSONException {
        try {
            if (userService.checkAuth(token)) {
                return userService.updateUser(id, updatedUser);
            } else {
                return new ResponseStatus().sessionNotAuthenticated();
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", ex);
        }
    }

}