package com.subject.flow.file.service;

import com.subject.flow.file.dto.FileRequestDto;
import com.subject.flow.file.entity.FileType;
import com.subject.flow.file.repository.FileTypeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import static com.subject.flow.constant.AppConstants.*;

@Service
@RequiredArgsConstructor
public class FileTypeServiceImpl implements FileTypeService {
    private final FileTypeRepository fileTypeRepository;

    @Transactional
    public FileType save(FileRequestDto dto) {

        if(FileType.customFileSize(findAll(), FIX_EXTENSIONS) >= EXTENSION_MAX_SIZE){
            throw new ArrayIndexOutOfBoundsException("확장자의 범위를 벗어났습니다.");
        }

        // 처음으로 저장된 파일 타입
        Optional<FileType> fileType = this.fileTypeRepository.findByName(dto.getFileType());

        if(fileType.isEmpty())
            return this.fileTypeRepository.save(FileType.builder()
                .saved(true)
                .name(dto.getFileType())
                .build());
        FileType entity = fileType.get();

        // 파일타입이 존재하고, 체크박스가 true일때
        entity.saved(dto.isSaved());

        return this.fileTypeRepository.save(entity);
    }

    @Transactional
    public List<FileRequestDto> findAll() {
        return this.fileTypeRepository.findAll()
                .stream()
                .filter(FileType::isSaved)
                .map(entity -> FileRequestDto.builder()
                        .fileType(entity.getName())
                        .isSaved(entity.isSaved())
                        .build())
                .toList();
    }

}
