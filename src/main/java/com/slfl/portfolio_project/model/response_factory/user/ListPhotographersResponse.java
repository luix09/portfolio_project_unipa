package com.slfl.portfolio_project.model.response_factory.user;

import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.model.user.Photographer;
import java.util.List;

public class ListPhotographersResponse extends CustomResponse {
    public ListPhotographersResponse(String code, String message, List<Photographer> data) {
        super(code, message, data);
    }
}
