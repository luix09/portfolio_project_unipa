package com.slfl.portfolio_project.model.response_factory.picture;

import com.slfl.portfolio_project.model.Picture;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.model.response_factory.ResponseFactory;

import java.util.List;

public class PictureResponseFactory extends ResponseFactory {
    @Override
    public CustomResponse createDataResponse(Object data) {
        return new PictureResponse("200", "Immagine selezionata con successo", (Picture) data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CustomResponse createListDataResponse(Object data) {
        return new ListPicturesResponse("200", "Immagini selezionate con successo.", (List<Picture>) data);
    }

    @Override
    public CustomResponse createSuccessfullyResponse() {
        return new PictureResponse("204", "Immagine creata con successo");
    }

    @Override
    public CustomResponse deleteSuccessfullyResponse() {
        return new PictureResponse("204", "Immagine eliminata con successo");
    }

    @Override
    public CustomResponse updateSuccessfullyResponse() {
        return new PictureResponse("204", "Immagine aggiornata con successo");
    }

    @Override
    public CustomResponse createCustomError(String code, String message) {
        return new PictureError(code, message);
    }
}
