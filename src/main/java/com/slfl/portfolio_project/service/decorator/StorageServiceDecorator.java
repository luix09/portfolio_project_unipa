package com.slfl.portfolio_project.service.decorator;

import com.slfl.portfolio_project.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public class StorageServiceDecorator implements StorageService {
    private final StorageService storageService;

    public StorageServiceDecorator(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public void init() {
        storageService.init();
    }

    @Override
    public void store(MultipartFile file, String userDir) {
        storageService.store(file, userDir);
    }

    @Override
    public Stream<Path> loadAll(String userDir) {
        return storageService.loadAll(userDir);
    }

    @Override
    public Path load(String filename) {
        return storageService.load(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return storageService.loadAsResource(filename);
    }

    @Override
    public Stream<Path> loadAllByDirectory(String directory) {
        return null;
    }

    @Override
    public Path loadByDirectory(String filename, String directory) {
        return storageService.loadByDirectory(filename, directory);
    }

    @Override
    public void deleteAll() {
        storageService.deleteAll();
    }
}
