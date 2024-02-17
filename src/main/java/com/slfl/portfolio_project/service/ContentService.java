package com.slfl.portfolio_project.service;

import com.slfl.portfolio_project.model.Album;
import com.slfl.portfolio_project.model.Picture;
import com.slfl.portfolio_project.model.ResponseStatus;
import com.slfl.portfolio_project.repository.AlbumRepository;
import com.slfl.portfolio_project.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PictureRepository pictureRepository;

    public ResponseStatus getMostLikedAlbums() {
        try {
            List<Album> mostLikedAlbums = albumRepository.findMostLikedAlbums();
            return new ResponseStatus().generalSuccess();
        } catch(Exception e) {
            return new ResponseStatus().generalError(e);
        }
    }

    public ResponseStatus getMostLikedPictures() {
        try {
            List<Picture> mostLikedPictures = pictureRepository.findMostLikedPictures();
            return new ResponseStatus().generalSuccess();
        } catch(Exception e) {
            return new ResponseStatus().generalError(e);
        }
    }
}
