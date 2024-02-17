package com.slfl.portfolio_project.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImageFileService {
    private final PictureService pictureService;

    @Autowired
    public ImageFileService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public void storeFileToPictureTable(String path, Integer pictureId) throws Exception {
        pictureService.updateFilePicture(pictureId, path);
    }
}
