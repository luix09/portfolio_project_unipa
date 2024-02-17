package com.slfl.portfolio_project.controller;

import com.slfl.portfolio_project.misc.errors.StorageFileNotFoundException;
import com.slfl.portfolio_project.model.requests.PictureCreateDTO;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.repository.UserRepository;
import com.slfl.portfolio_project.service.ImageFileService;
import com.slfl.portfolio_project.service.PictureService;
import com.slfl.portfolio_project.service.StorageService;
import com.slfl.portfolio_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.util.stream.Stream;

@RestController
@RequestMapping("/pictures")
@CrossOrigin("*")
public class PictureController {

    @Autowired
    private PictureService service;

    @GetMapping("/picture/read/{pictureId}")
    public CustomResponse readPicture(@PathVariable Integer pictureId) {
        return service.getSinglePicture(pictureId);
    }

    //TODO: to be tested
    @GetMapping("/{albumId}")
    public ResponseEntity<Stream<Path>> getImagesOfAlbum(@PathVariable Integer albumId) {
        try {
            // Load the image resource
            Stream<Path> resource = (Stream<Path>) service.loadFileImageByAlbum(albumId).getData();

            // Set the Content-Type header based on the media type of the image
            MediaType mediaType = MediaType.IMAGE_JPEG; // or MediaType.IMAGE_PNG, etc.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);

            // Return the image as a ResponseEntity
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            // Handle exceptions (e.g., image not found) and return an appropriate response
            return ResponseEntity.notFound().build();
        }
    }
}
