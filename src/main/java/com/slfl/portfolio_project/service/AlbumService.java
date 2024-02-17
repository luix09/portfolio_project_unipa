package com.slfl.portfolio_project.service;

import com.slfl.portfolio_project.model.Album;
import com.slfl.portfolio_project.model.Picture;
import com.slfl.portfolio_project.model.ResponseStatus;
import com.slfl.portfolio_project.model.User;
import com.slfl.portfolio_project.model.requests.AlbumCreateDTO;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.model.response_factory.ResponseFactory;
import com.slfl.portfolio_project.model.response_factory.album.AlbumResponseFactory;
import com.slfl.portfolio_project.repository.AlbumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final ResponseFactory responseFactory;

    @Autowired
    AlbumService(AlbumRepository albumRepository, UserService userService) {
        this.albumRepository = albumRepository;
        this.userService = userService;
        this.responseFactory = new AlbumResponseFactory();
    }

    public CustomResponse createAlbum(AlbumCreateDTO albumCreateDTO, String token) {
        try {
            User owner = userService.getUserFromToken(token);
            Optional<Album> retrievedAlbum = albumRepository.findAlbumByTitle(albumCreateDTO.getTitle());
            if (retrievedAlbum.isPresent()) {
                return this.responseFactory.createCustomError("404", "Album già presente.");
            }

            albumRepository.save(new Album(albumCreateDTO.getTitle(), albumCreateDTO.getDescription(), albumCreateDTO.getDate(), new ArrayList<Picture>(), new ArrayList<User>(), owner));

            return this.responseFactory.createSuccessfullyResponse();
        } catch (Exception e) {
            return this.responseFactory.createCustomError("404", e.getMessage());
        }
    }

    public CustomResponse getAllPhotographerAlbums(String photographer) {
        try {
            User pUser = userService.getUserByUsername(photographer);
            Optional<List<Album>> allAlbums = albumRepository.findAllByOwner(pUser);

            if(allAlbums.isEmpty() || allAlbums.get().isEmpty()) {
                return this.responseFactory.createCustomError("404", "Nessun album trovato per questo fotografo.");
            }

            return this.responseFactory.createListDataResponse(allAlbums.get());
        } catch (Exception e) {
            return this.responseFactory.createCustomError("404", e.getMessage());
        }
    }

    public CustomResponse getPhotographerAlbum(String photographer, Integer albumId) {
        try {
            User pUser = userService.getUserByUsername(photographer);
            Optional<Album> allAlbums = albumRepository.findByOwnerAndAlbumId(pUser, albumId);

            if(allAlbums.isEmpty()) {
                return this.responseFactory.createCustomError("404", "Album non trovato.");
            }

            return this.responseFactory.createDataResponse(allAlbums.get());
        } catch (Exception e) {
            return this.responseFactory.createCustomError("404", e.getMessage());
        }
    }

    public CustomResponse deleteAlbum(Integer albumId) {
        try {
            Optional<Album> specifiedAlbum = albumRepository.findById(albumId);
            if (specifiedAlbum.isEmpty()) {
                return this.responseFactory.createCustomError("404", "Album non trovato.");
            }
            albumRepository.delete(specifiedAlbum.get());
            return this.responseFactory.deleteSuccessfullyResponse();
        } catch (Exception e) {
            return this.responseFactory.createCustomError("404", e.getMessage());
        }
    }

    public CustomResponse updateAlbum(Integer albumId, AlbumCreateDTO albumCreateDTO, String token) {
        try {
            User owner = userService.getUserFromToken(token);
            Optional<Album> specifiedAlbum = albumRepository.findById(albumId);
            if (specifiedAlbum.isEmpty()) {
                return this.responseFactory.createCustomError("404", "Album non trovato.");
            }

            albumRepository.save(new Album(albumId, albumCreateDTO.getTitle(), albumCreateDTO.getDescription(), albumCreateDTO.getDate(), new ArrayList<Picture>(), new ArrayList<User>(), owner));
            return this.responseFactory.updateSuccessfullyResponse();
        } catch (Exception e) {
            return this.responseFactory.createCustomError("404", e.getMessage());
        }
    }
}
