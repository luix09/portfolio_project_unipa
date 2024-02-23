package com.slfl.portfolio_project.model.response_factory.album;

import com.slfl.portfolio_project.model.Album;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;

public class AlbumResponse extends CustomResponse {
    public AlbumResponse(String code, String message, Album album) {
        super(code, message, album);
    }

    public AlbumResponse(String code, String message) {
        super(code, message);

    }
}
