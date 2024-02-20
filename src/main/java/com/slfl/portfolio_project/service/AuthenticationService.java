package com.slfl.portfolio_project.service;

import java.util.*;

import com.slfl.portfolio_project.model.*;
import com.slfl.portfolio_project.model.user.Customer;
import com.slfl.portfolio_project.model.user.Photographer;
import com.slfl.portfolio_project.model.user.User;
import com.slfl.portfolio_project.repository.CustomerRepository;
import com.slfl.portfolio_project.repository.PhotographerRepository;
import com.slfl.portfolio_project.repository.RoleRepository;
import com.slfl.portfolio_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private PhotographerRepository photographerRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

    public ResponseStatus registerUser(String username, String email, String password, boolean isPhotographer) {
        ResponseStatus response = new ResponseStatus();
        User user = new User();
        try {
            RoleType roleType = isPhotographer ? RoleType.PHOTOGRAPHER : RoleType.CUSTOMER;
            if (user.isValidPassword(password)) {
                String encodedPassword = passwordEncoder.encode(password);

                if (roleRepository.findByRoleName(roleType).isEmpty()) {
                    roleRepository.save(new Role(roleType));
                }

                Role userRole = roleRepository.findByRoleName(roleType).get();

                if(isPhotographer) {
                    user = photographerRepository.save(new Photographer(username, email, encodedPassword, userRole));
                } else {
                    user = customerRepository.save(new Customer(username, email, encodedPassword, userRole));
                }
                if (user.getUserId() != 0 && user.getUserId() != null) {
                    return response.registrationSuccess(user);
                }
            } else {
                return response.passwordLowSecurity();
            }
        } catch (Exception e) {
            return response.generalError(e);
        }

        return response;
    }

    public ResponseStatus loginUser(String username, String password) {
        ResponseStatus response = new ResponseStatus();
        try {
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isEmpty()) {
                return response.userNotFound();
            }
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            return response.loginSuccess(new ResponseData(user.get(), token));

        } catch (BadCredentialsException e) {
            return response.invalidCredentials(e);
        } catch (AuthenticationException e) {
            return response.generalError(e);
        }
    }

    public ResponseStatus checkExistUser(String username, String email) {
        ResponseStatus response = new ResponseStatus();
        List<User> emailUser = userRepository.findByEmail(email);
        if (!emailUser.isEmpty()) {
            return response.emailAlreadyUsed();
        }
        Optional<User> usernameUser = userRepository.findByUsername(username);
        if (usernameUser.isPresent()) {
            return response.usernameAlreadyUsed();
        }
        return response.generalSuccess();
    }

    public ResponseStatus checkExistUserLogin(String username) {
        ResponseStatus response = new ResponseStatus();
        Optional<User> usernameUser = userRepository.findByUsername(username);
        if (usernameUser.isEmpty()) {
            return response.userNotFound();
        }
        return response.generalSuccess();
    }
}