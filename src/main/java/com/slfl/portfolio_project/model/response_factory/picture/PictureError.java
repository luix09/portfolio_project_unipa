package com.slfl.portfolio_project.model.response_factory.picture;

import com.slfl.portfolio_project.model.response_factory.CustomResponse;

public class PictureError extends CustomResponse {
    public PictureError(String code, String message) {
        super(code, message);
    }
}
