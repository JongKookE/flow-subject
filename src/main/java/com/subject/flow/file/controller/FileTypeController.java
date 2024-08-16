package com.subject.flow.file.controller;

import com.subject.flow.file.dto.FileRequestDto;
import com.subject.flow.file.entity.FileType;
import com.subject.flow.file.service.FileTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/types")
public class FileTypeController {
    private final FileTypeService fileTypeService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<FileType> saveFileType(@RequestBody FileRequestDto fileRequestDto) {
        return ResponseEntity.ok(this.fileTypeService.save(fileRequestDto));
    }
}
