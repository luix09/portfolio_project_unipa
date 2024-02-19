package com.slfl.portfolio_project.service;

import com.slfl.portfolio_project.model.Album;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
@Transactional
public class ImageFileService {
    private final PictureService pictureService;
    @Autowired
    private UserService userService;

    @Autowired
    private StorageService storageService;

    @Autowired
    public ImageFileService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public void storeFileToPictureTable(String path, Integer pictureId) throws Exception {
        pictureService.updateFilePicture(pictureId, path);
    }

    public Stream<Path> loadImagesOfAlbum(Album album) throws Exception {
        String username = userService.getUserByAlbumId(album.getAlbumId()).getUsername();

        // Load the image resource
        return storageService.loadAllByDirectory(username + album.getTitle());
    }
}
