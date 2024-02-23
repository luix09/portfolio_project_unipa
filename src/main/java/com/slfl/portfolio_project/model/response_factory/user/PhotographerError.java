package com.slfl.portfolio_project.model.response_factory.user;

import com.slfl.portfolio_project.model.response_factory.CustomResponse;

public class PhotographerError extends CustomResponse {
    public PhotographerError(String code, String message) {
        super(code, message);
    }
}
