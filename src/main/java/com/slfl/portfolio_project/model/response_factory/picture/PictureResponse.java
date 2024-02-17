package com.slfl.portfolio_project.model.response_factory.picture;

import com.slfl.portfolio_project.model.Picture;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;

public class PictureResponse extends CustomResponse {
    public PictureResponse(String code, String message, Picture data) {
        super(code, message, data);
    }

    public PictureResponse(String code, String message) {
        super(code, message);
    }
}
