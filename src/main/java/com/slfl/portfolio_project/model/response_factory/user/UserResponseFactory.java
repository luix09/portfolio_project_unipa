package com.slfl.portfolio_project.model.response_factory.user;

import com.slfl.portfolio_project.model.user.Photographer;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.model.response_factory.ResponseFactory;

import java.util.List;

public class UserResponseFactory extends ResponseFactory {
    @Override
    public CustomResponse createDataResponse(Object data) {
        return new PhotographerInfoResponse("200", "Informazioni del fotografo ottenute con successo.", (Photographer) data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CustomResponse createListDataResponse(Object data) {
        return new ListPhotographersResponse("200", "Lista di fotografi ritornata con successo.", (List<Photographer>) data);
    }

    @Override
    public CustomResponse createSuccessfullyResponse() {
        return null;
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
