package com.example.demo.service;

import com.example.demo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;
    private final String bucketName = "demo-users";

    public void saveUserToS3(UserDto userDto) {
        try {
            // Создаем содержимое файла
            String fileContent = String.format(
                    "User Information:\nName: %s\nEmail: %s\nTimestamp: %s",
                    userDto.getName(),
                    userDto.getEmail(),
                    LocalDateTime.now()
            );

            String fileName = generateFileName(userDto);

            InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));

            uploadFile(fileName, inputStream, fileContent.length());

        } catch (Exception e) {
            throw new RuntimeException("Failed to save user data to S3", e);
        }
    }

    private String generateFileName(UserDto userDto) {
        String safeName = userDto.getName().replaceAll("[^a-zA-Z0-9]", "_");
        String safeEmail = userDto.getEmail().replaceAll("[^a-zA-Z0-9@._-]", "_");

        return String.format("%s_%s.txt", safeName, safeEmail);
    }

    private void uploadFile(String key, InputStream inputStream, long contentLength) {
        try {
            // Проверяем существование бакета
            if (!s3Client.listBuckets().buckets().stream()
                    .anyMatch(b -> b.name().equals(bucketName))) {
                s3Client.createBucket(b -> b.bucket(bucketName));
            }

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType("text/plain")
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(inputStream, contentLength));

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }
}