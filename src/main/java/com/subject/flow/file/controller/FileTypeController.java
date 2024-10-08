package com.subject.flow.file.controller;

import com.subject.flow.file.dto.FileRequestDto;
import com.subject.flow.file.entity.FileType;
import com.subject.flow.file.service.FileTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<FileRequestDto>> findAll() {
        return ResponseEntity.ok(this.fileTypeService.findAll());
    }
}
