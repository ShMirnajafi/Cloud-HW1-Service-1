package com.example.demo.objectstorage;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ObjectStorageService {

    @Value("${liara.object-storage.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    public ObjectStorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(String fileName, Path filePath) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, filePath);
    }
}
