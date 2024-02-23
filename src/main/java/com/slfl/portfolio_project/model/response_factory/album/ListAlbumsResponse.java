package com.slfl.portfolio_project.model.response_factory.album;

import com.slfl.portfolio_project.model.Album;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;

import java.util.List;

public class ListAlbumsResponse extends CustomResponse {
    ListAlbumsResponse(String code, String message, List<Album> albums) {
        super(code, message, albums);
    }
}
