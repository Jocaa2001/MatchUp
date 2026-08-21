package com.matchup.filestorage;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileStorageService {

    String upload(MultipartFile file);

    InputStream get(String fileName);

    void delete(String key);
}
