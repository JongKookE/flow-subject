package com.subject.flow.file.controller;

import com.subject.flow.file.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {
    private final UploadService uploadService;

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String fileName = this.uploadService.uploadFile(file);
        return ResponseEntity.ok(this.uploadService.toJsonForm(fileName));
    }
}
