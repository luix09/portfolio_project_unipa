package com.slfl.portfolio_project.service;

import com.slfl.portfolio_project.configuration.StorageProperties;
import com.slfl.portfolio_project.misc.errors.StorageException;
import com.slfl.portfolio_project.misc.errors.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties) {
        if(storageProperties.getLocation().trim().isEmpty()){
            throw new StorageException("File upload location can not be empty.");
        }

        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(MultipartFile file, String userDir) {
        try {
            Path userPath = this.rootLocation.resolve(userDir);
            Files.createDirectories(userPath);
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String fileName = file.getOriginalFilename();
            if (fileName == null) {
                throw new StorageException("Invalid file name provided.");
            }
            Path destinationFile = userPath.resolve(
                            Paths.get(fileName))
                    .normalize().toAbsolutePath();
            Path absolute = this.rootLocation.toAbsolutePath();
            Path parent = destinationFile.getParent().getParent();
            if (!parent.equals(absolute)) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll(String userId) {
        try {
            return Files.walk(this.rootLocation.resolve(userId), 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Stream<Path> loadAllByDirectory(String directory) {
        try {
            return Files.walk(this.rootLocation.resolve(directory), 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }


    public Path loadByDirectory(String filename, String directory) {
        return rootLocation.resolve(directory + "/" + filename);
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
