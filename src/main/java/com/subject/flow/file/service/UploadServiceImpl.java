package com.subject.flow.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@Slf4j
@Service
public class UploadServiceImpl implements UploadService{
    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public String uploadFile(MultipartFile file)  {
        String originalFilename = file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try{
            s3Client.putObject(bucket, originalFilename, file.getInputStream(), metadata);
        } catch(MaxUploadSizeExceededException e){
            throw new RuntimeException("파일이 너무 커서 저장할 수 없습니다.");
        } catch (IOException e) {
            throw new RuntimeException("파일을 저장하지 못하였습니다.");
        }

        return originalFilename;
    }

    public String toJsonForm(String name){
        return "{\"message\":\""+name+"\"}";
    }
}
