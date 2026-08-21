package com.matchup.filestorage.minio;


import com.matchup.filestorage.FileStorageService;
import io.minio.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioStorageService implements FileStorageService {

    private final MinioClient minioClient;

    public MinioStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String upload(MultipartFile file) {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("avatar")
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1L)
                            .contentType(file.getContentType())
                            .build()
            );
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public InputStream get(String fileName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket("avatar")
                            .object(fileName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file", e);
        }
    }

    @Override
    public void delete(String key) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket("avatar")
                            .object(key)
                            .build()
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file", e);
        }
    }
}
