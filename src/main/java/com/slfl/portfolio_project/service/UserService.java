package com.slfl.portfolio_project.service;

import com.slfl.portfolio_project.model.ResponseStatus;
import com.slfl.portfolio_project.model.user.User;
import com.slfl.portfolio_project.model.requests.UpdateUserDTORequest;
import com.slfl.portfolio_project.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final TokenService tokenService = new TokenService();

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public ResponseStatus updateUser(int userId, UpdateUserDTORequest userDTORequest) {
        ResponseStatus response = new ResponseStatus();
        try {
            if (userRepository.findById(userId).isPresent()) {
                User user = userRepository.findById(userId).get();
                if (encoder.matches(userDTORequest.getPassword(), user.getPassword())) {
                    userRepository.save(new User(user.getUserId(), userDTORequest.getName(), userDTORequest.getSurname(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole()));
                    return response.updatedUserSuccessfully();
                } else {
                    return response.invalidPassword();
                }
            } else {
                return response.userNotFound();
            }
        } catch (Exception e) {
            return response.generalError(e);
        }
    }

    public User getUserByUsername(String username) {
        try {
            Optional<User> selectedUser = userRepository.findByUsername(username);
            return selectedUser.orElse(null);
        } catch(Exception ex) {
            return null;
        }
    }

    public User getUserFromToken(String token) throws UnsupportedEncodingException, JSONException, UsernameNotFoundException {
        JSONObject decodedToken = tokenService.decodeJwt(token);
        String info = decodedToken.getString("sub");
        if(userRepository.findByUsername(info).isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        return userRepository.findByUsername(info).get();
    }

    public boolean checkAuth(String token) {
        try {
            JSONObject decodedToken = tokenService.decodeJwt(token);
            String info = decodedToken.getString("sub");
            userRepository.findByUsername(info).get();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getUserByAlbumId(Integer albumId) throws Exception {
        Optional<User> foundUser = userRepository.findByAlbumId(albumId);

        if(foundUser.isEmpty()) {
            throw new Exception("Album non associato ad alcun utente!");
        }
        return foundUser.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }

    public List<User> getAllPhotographers() {
        return userRepository.findAllPhotographer();
    }
}