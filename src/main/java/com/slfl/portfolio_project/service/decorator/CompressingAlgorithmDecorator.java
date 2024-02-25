package com.slfl.portfolio_project.service.decorator;

import com.slfl.portfolio_project.service.StorageService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class CompressingAlgorithmDecorator extends StorageServiceDecorator {
    public CompressingAlgorithmDecorator(StorageService fileSystemStorageService) {
        super(fileSystemStorageService);
    }

    public void compressFile(MultipartFile mpFile) {
        // Fake Compression
        try {
            System.out.println("Compressione del file " + mpFile.getOriginalFilename() + " effettuata!");
        } catch (Exception e) {
            System.out.println("Errori durante la compressione del file.");
        }
    }


    @Override
    public void store(MultipartFile file, String userDir) {
        compressFile(file);
        //we should transform byte array to multipart file again
        super.store(file, userDir);
    }
}
