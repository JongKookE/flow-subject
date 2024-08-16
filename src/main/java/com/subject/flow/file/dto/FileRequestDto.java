package com.subject.flow.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FileRequestDto {
    private String fileType;
    private boolean isSaved;
}
