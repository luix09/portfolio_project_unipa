package com.slfl.portfolio_project.model.response_factory.image;

import com.slfl.portfolio_project.model.response_factory.CustomResponse;

public class LoadedImageResponse extends CustomResponse {
    public LoadedImageResponse(String code, String message, Object data) {
        super(code, message, data);
    }
}
