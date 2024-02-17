package com.slfl.portfolio_project.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    void store(MultipartFile file, String userDir);

    Stream<Path> loadAll(String userDir);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
