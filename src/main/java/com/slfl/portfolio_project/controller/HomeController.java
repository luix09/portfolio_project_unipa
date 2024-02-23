package com.slfl.portfolio_project.controller;

import com.slfl.portfolio_project.model.user.User;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.model.response_factory.ResponseFactory;
import com.slfl.portfolio_project.model.response_factory.user.UserResponseFactory;
import com.slfl.portfolio_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@CrossOrigin("*")
public class HomeController {
    @Autowired
    private UserService userService;
    private final ResponseFactory responseFactory;
    public HomeController() {
        responseFactory = new UserResponseFactory();
    }
    @GetMapping("list/photographer")
    public CustomResponse getListOfPhotographers() {
        try {
            List<User> users = userService.getAllPhotographers();
            return this.responseFactory.createListDataResponse(users);
        } catch (Exception e) {
            return this.responseFactory.createCustomError("404", "Impossibile fornire la lista di fotografi");
        }
    }

    @GetMapping("/photographer/{username}")
    public CustomResponse getPhotographerInfo(@PathVariable String username) {
        try {
            return this.responseFactory.createDataResponse(userService.getUserByUsername(username));
        } catch (Exception e) {
            return this.responseFactory.createCustomError("404", "Impossibile fornire le informazioni di questo fotografo.");
        }
    }
}
