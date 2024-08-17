package com.subject.flow.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadFile(MultipartFile file);
    String toJsonForm(String name);
}
