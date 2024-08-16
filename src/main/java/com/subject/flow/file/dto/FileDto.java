package com.subject.flow.file.dto;

import com.subject.flow.file.entity.FileType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileDto {
    private FileType fileType;
}
