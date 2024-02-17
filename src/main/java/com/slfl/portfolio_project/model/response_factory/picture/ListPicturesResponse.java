package com.slfl.portfolio_project.model.response_factory.picture;

import com.slfl.portfolio_project.model.Picture;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;

import java.util.List;

public class ListPicturesResponse extends CustomResponse {
    public ListPicturesResponse(String code, String message, List<Picture> listPictures) {
        super(code, message, listPictures);
    }
}
