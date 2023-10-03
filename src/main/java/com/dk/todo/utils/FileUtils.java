package com.dk.todo.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.net.URL;

@Component
public class FileUtils {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    private final AmazonS3Client amazonS3Client;

    private static final String HTTPS_PREFIX = "https://";


    public FileUtils(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }


    public String fileUpload(MultipartFile multipartFile, Long userId) {


        String fileName = multipartFile.getOriginalFilename();
//        String fileUrl = HTTPS_PREFIX + bucket + "/" + userId.toString() + "/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());

        try (InputStream inputStream = multipartFile.getInputStream()) {

            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3Client.getUrl(bucket, fileName).toString();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
