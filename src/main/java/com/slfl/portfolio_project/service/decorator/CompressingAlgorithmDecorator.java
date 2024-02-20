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

    public ByteArrayOutputStream compressFile(MultipartFile mpFile) {
        float quality = 0.3f;
        String imageName = mpFile.getOriginalFilename();
        assert imageName != null;
        String imageExtension = imageName.substring(imageName.lastIndexOf(".") + 1);
        // Returns an Iterator containing all currently registered ImageWriters that claim to be able to encode the named format.
        // You don't have to register one yourself; some are provided.
        ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(imageExtension).next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Check the api value that suites your needs.
        // A compression quality setting of 0.0 is most generically interpreted as "high compression is important,"
        // while a setting of 1.0 is most generically interpreted as "high image quality is important."
        imageWriteParam.setCompressionQuality(quality);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // MemoryCacheImageOutputStream: An implementation of ImageOutputStream that writes its output to a regular
        // OutputStream, i.e. the ByteArrayOutputStream.
        ImageOutputStream imageOutputStream = new MemoryCacheImageOutputStream(baos);
        // Sets the destination to the given ImageOutputStream or other Object.
        imageWriter.setOutput(imageOutputStream);
        BufferedImage originalImage = null;
        try (InputStream inputStream = mpFile.getInputStream()) {
            originalImage = ImageIO.read(inputStream);
            ImageIO.wr
        } catch (IOException e) {
            String info = String.format("compressImage - bufferedImage (file %s)- IOException - message: %s ", imageName, e.getMessage());
            System.out.println(info);
            return baos;
        }
        IIOImage image = new IIOImage(originalImage, null, null);
        try {
            imageWriter.write(null, image, imageWriteParam);
        } catch (IOException e) {
            String info = String.format("compressImage - imageWriter (file %s)- IOException - message: %s ", imageName, e.getMessage());
            System.out.println(info);
        } finally {
            imageWriter.dispose();
        }
        return baos;
    }


    @Override
    public void store(MultipartFile file, String userDir) {
        ByteArrayOutputStream baos = compressFile(file);
        //we should transform byte array to multipart file again
        super.store(file, userDir);
    }
}
