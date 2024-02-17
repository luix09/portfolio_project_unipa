package com.slfl.portfolio_project.model.response_factory.album;

import com.slfl.portfolio_project.model.response_factory.CustomError;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;

public class AlbumError extends CustomResponse {
    public AlbumError(String code, String message) {
        super(code, message);
    }
}
