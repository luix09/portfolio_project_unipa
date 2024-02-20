package com.slfl.portfolio_project.controller;

import com.slfl.portfolio_project.misc.errors.StorageFileNotFoundException;
import com.slfl.portfolio_project.model.Album;
import com.slfl.portfolio_project.model.requests.AlbumCreateDTO;
import com.slfl.portfolio_project.model.requests.PictureCreateDTO;
import com.slfl.portfolio_project.model.response_factory.CustomResponse;
import com.slfl.portfolio_project.model.response_factory.picture.PictureError;
import com.slfl.portfolio_project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/photographer")
@CrossOrigin("*")
public class PhotographerController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ImageFileService imageFileService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhotographerService photographerService;

    @PostMapping("/album/create")
    public CustomResponse createAlbum(@RequestBody AlbumCreateDTO albumCreateDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return albumService.createAlbum(albumCreateDTO, token);
    }

    @PostMapping("/album/delete/{albumId}")
    public CustomResponse deleteAlbum(@PathVariable Integer albumId) {
        return albumService.deleteAlbum(albumId);

    }

    @PostMapping("/album/update/{albumId}")
    public CustomResponse updateAlbum(@PathVariable Integer albumId, @RequestBody AlbumCreateDTO albumCreateDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return albumService.updateAlbum(albumId, albumCreateDTO, token);
    }

    @PostMapping("/picture/create/{albumId}")
    public CustomResponse createPicture(@RequestHeader(HttpHeaders.AUTHORIZATION) String token , @PathVariable Integer albumId, @RequestBody PictureCreateDTO pictureCreateDTO) {
        try {
            CustomResponse response = pictureService.createPicture(pictureCreateDTO, albumId);
            photographerService.notifyFollowers(token);
            return response;
        } catch (Exception ex) {
            return new PictureError("404", "Errore durante la creazione dell'immagine");
        }
    }

    //TODO: to be tested
    @PostMapping("/picture/upload/{pictureId}")
    public String handlePictureUpload(@RequestParam("file") MultipartFile file,
                                      RedirectAttributes redirectAttributes, @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Integer pictureId) {
        try {
            // needed to get userDir/albumTitle structure for the directory
            String userDir = userService.getUserFromToken(token).getUsername();
            Album album = albumService.getAlbumIdByPictureId(pictureId);

            // Store inside that directory
            storageService.store(file, userDir + album.getTitle());
            String path = storageService.load(file.getOriginalFilename()).toUri().getPath();
            // Storing path to database
            imageFileService.storeFileToPictureTable(path, pictureId);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",
                    "Error while uploading " + file.getOriginalFilename() + "!" + "\nError: " + e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/picture/update/{pictureId}")
    public CustomResponse updatePicture(@PathVariable Integer pictureId, @RequestBody PictureCreateDTO pictureCreateDTO) {
        return pictureService.updatePicture(pictureId, pictureCreateDTO);
    }

    @PostMapping("/picture/delete/{pictureId}")
    public CustomResponse deletePicture(@PathVariable Integer pictureId) {
        return pictureService.deletePicture(pictureId);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
