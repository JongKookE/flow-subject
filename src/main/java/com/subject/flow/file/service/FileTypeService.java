package com.subject.flow.file.service;

import com.subject.flow.file.dto.FileRequestDto;
import com.subject.flow.file.entity.FileType;

import java.util.List;

public interface FileTypeService {
    FileType save(FileRequestDto dto);
    List<FileRequestDto> findAll();
}
