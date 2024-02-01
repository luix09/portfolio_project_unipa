package com.slfl.portfolio_project.service;

import java.util.*;
import java.util.regex.Pattern;

import com.slfl.portfolio_project.model.LoginResponseDTO;
import com.slfl.portfolio_project.model.Role;
import com.slfl.portfolio_project.model.User;
import com.slfl.portfolio_project.model.ResponseStatus;
import com.slfl.portfolio_project.repository.RoleRepository;
import com.slfl.portfolio_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseStatus registerUser(String username, String email, String password){
        ResponseStatus response = new ResponseStatus();
        User user = new User();
        try{
            if(user.isValidPassword(password)){
                String encodedPassword = passwordEncoder.encode(password);
                Role userRole = roleRepository.findByAuthority("USER").get();
                Set<Role> authorities = new HashSet<>();

                authorities.add(userRole);

                userRepository.findByUsername(username);
                user = userRepository.save(new User(0, username, email, encodedPassword, authorities));
                if(user.getUserId() != 0 && user.getUserId() != null ){
                    return response.registrationSuccess(username);
                }
            }else{
                return response.passwordLowSecurity();
            }
        }catch (Exception e){
            return response.generalError(e);
        }

        return response;
    }

    public ResponseStatus loginUser(String username, String password){
        ResponseStatus response = new ResponseStatus();
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);
            return response.loginSuccess(token);
        } catch(AuthenticationException e){
            return response.generalError(e);
        }
    }

    public ResponseStatus checkExistUser(String username, String email) {
        ResponseStatus response = new ResponseStatus();
        List<User> emailUser = userRepository.findByEmail(email);
        if(!emailUser.isEmpty()){
            return response.emailAlreadyUsed();
        }
        Optional<User> usernameUser = userRepository.findByUsername(username);
        if(!usernameUser.isEmpty()){
            return response.usernameAlreadyUsed();
        }
        return response.generalSuccess();
    }

    public ResponseStatus checkExistUserLogin(String username) {
        ResponseStatus response = new ResponseStatus();
        Optional<User> usernameUser = userRepository.findByUsername(username);
        if(usernameUser.isEmpty()){
            return response.userNotFound();
        }

        return response.generalSuccess();
    }
}