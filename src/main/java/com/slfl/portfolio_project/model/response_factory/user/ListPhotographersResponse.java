package com.slfl.portfolio_project.model.response_factory.user;

import com.slfl.portfolio_project.model.User;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;

import java.util.List;

public class ListPhotographersResponse extends CustomResponse {
    public ListPhotographersResponse(String code, String message, List<User> data) {
        super(code, message, data);
    }
}
