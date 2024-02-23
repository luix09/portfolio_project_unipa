package com.slfl.portfolio_project.controller;


import com.slfl.portfolio_project.model.ResponseStatus;
import com.slfl.portfolio_project.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
@CrossOrigin("*")

public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/mostLikedAlbums")
    public ResponseStatus getMostLikedAlbums() {
        try {
            return contentService.getMostLikedAlbums();
        } catch (Exception e) {
            return new ResponseStatus().generalError(e);
        }
    }

    @GetMapping("/mostLikedPictures")
    public ResponseStatus getMostLikedPictures() {
        try {
            return contentService.getMostLikedPictures();
        } catch (Exception e) {
            return new ResponseStatus().generalError(e);
        }
    }
}
