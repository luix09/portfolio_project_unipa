package com.slfl.portfolio_project.service;


import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import com.slfl.portfolio_project.model.ResponseStatus;
import com.slfl.portfolio_project.model.User;
import com.slfl.portfolio_project.model.requests.UserDTORequest;
import com.slfl.portfolio_project.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private final TokenService tokenService = new TokenService();

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }

    public ResponseStatus updateUser(int userId, UserDTORequest userDTORequest) {
        ResponseStatus response = new ResponseStatus();
        if(userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();
            userRepository.save(new User(user.getUserId(), userDTORequest.getUsername(), userDTORequest.getEmail(), userDTORequest.getPassword()));
            return response.updatedUserSuccessfully();
        } else {
            return response.userNotFound();
        }
    }

    public boolean checkAuth(String token) throws UnsupportedEncodingException, JSONException {
        try {
            JSONObject decodedToken = tokenService.decodeJwt(token);
            String info = decodedToken.getString("sub");
            User user = userRepository.findByUsername(info).get();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}