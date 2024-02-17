package com.slfl.portfolio_project.controller;

import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
@CrossOrigin("*")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    // Lettura singolo album
    @GetMapping("/read/{photographer}/{albumId}")
    public CustomResponse readPhotographerAlbum(@PathVariable String photographer, @PathVariable Integer albumId) {
        return albumService.getPhotographerAlbum(photographer, albumId);
    }

    // Lettura tutti album di un fotografo
    @GetMapping("/read/{photographer}")
    public CustomResponse readAllPhotographerAlbums(@PathVariable String photographer) {
        return albumService.getAllPhotographerAlbums(photographer);
    }
}
