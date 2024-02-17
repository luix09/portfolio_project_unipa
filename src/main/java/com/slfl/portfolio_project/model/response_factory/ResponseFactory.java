package com.slfl.portfolio_project.model.response_factory;

public abstract class ResponseFactory {
    public abstract CustomResponse createDataResponse(Object data);
    public abstract CustomResponse createListDataResponse(Object data);
    public abstract CustomResponse createSuccessfullyResponse();
    public abstract CustomResponse deleteSuccessfullyResponse();
    public abstract CustomResponse updateSuccessfullyResponse();
    public abstract CustomResponse createCustomError(String code, String message);
}
