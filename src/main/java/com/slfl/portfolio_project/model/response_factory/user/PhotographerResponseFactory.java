package com.slfl.portfolio_project.model.response_factory.user;

import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.model.response_factory.ResponseFactory;

public class PhotographerResponseFactory extends ResponseFactory {
    @Override
    public CustomResponse createDataResponse(Object data) {
        return null;
    }

    @Override
    public CustomResponse createListDataResponse(Object data) {
        return null;
    }

    @Override
    public CustomResponse createSuccessfullyResponse() {
        return new PhotographerFollowedSuccessfully("204", "Operazione eseguita con successo");
    }

    @Override
    public CustomResponse deleteSuccessfullyResponse() {
        return null;
    }

    @Override
    public CustomResponse updateSuccessfullyResponse() {
        return null;
    }

    @Override
    public CustomResponse createCustomError(String code, String message) {
        return null;
    }
}
