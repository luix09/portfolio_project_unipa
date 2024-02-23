package com.slfl.portfolio_project.model.response_factory.album;

import com.slfl.portfolio_project.model.Album;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.model.response_factory.ResponseFactory;

import java.util.List;

public class AlbumResponseFactory extends ResponseFactory {
    @Override
    public CustomResponse createDataResponse(Object data) {
        return new AlbumResponse("200", "Album selezionato con successo", (Album) data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CustomResponse createListDataResponse(Object data) {
        return new ListAlbumsResponse("200", "Album selezionati con successo.", (List<Album>) data);
    }

    @Override
    public CustomResponse createSuccessfullyResponse() {
        return new AlbumResponse("204", "Album creato con successo.");
    }

    @Override
    public CustomResponse deleteSuccessfullyResponse() {
        return new AlbumResponse("204", "Album eliminato con successo.");
    }

    @Override
    public CustomResponse updateSuccessfullyResponse() {
        return new AlbumResponse("204", "Album aggiornato con successo.");
    }

    @Override
    public CustomResponse createCustomError(String code, String message) {
        return new AlbumError(code, message);
    }
}
