package com.subject.flow.file.service;

import com.subject.flow.file.dto.FileRequestDto;
import com.subject.flow.file.entity.FileType;
import com.subject.flow.file.repository.FileTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileTypeService {
    private final FileTypeRepository fileTypeRepository;

    public FileType save(FileRequestDto dto) {
        // 처음으로 저장된 파일 타입
        Optional<FileType> fileType = this.fileTypeRepository.findByName(dto.getFileType());
        if(fileType.isEmpty())
            return this.fileTypeRepository.save(FileType.builder()
                .saved(true)
                .name(dto.getFileType())
                .build());
        FileType entity = fileType.get();

        // 파일타입이 존재하고, 체크박스가 true일때
        entity.setSaved(dto.isSaved());

        return this.fileTypeRepository.save(entity);
    }

    public List<FileRequestDto> findAll() {
        return this.fileTypeRepository.findAll()
                .stream()
                .map(entity -> FileRequestDto.builder()
                        .fileType(entity.getName())
                        .isSaved(entity.isSaved())
                        .build())
                .toList();
    }

}
