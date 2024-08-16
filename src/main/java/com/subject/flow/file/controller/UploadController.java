package com.subject.flow.file.controller;

import com.subject.flow.file.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class UploadController {
    private final UploadService uploadService;

    @PostMapping
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = this.uploadService.uploadFile(file);
        return this.uploadService.toJsonForm(fileName);
    }
}
