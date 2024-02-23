package com.slfl.portfolio_project.model.response_factory.user;

import com.slfl.portfolio_project.model.user.User;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;

public class PhotographerInfoResponse extends CustomResponse {
    public PhotographerInfoResponse(String code, String message, User data) {
        super(code, message, data);
    }
}
