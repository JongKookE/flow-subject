package com.subject.flow;

import com.subject.flow.file.dto.FileRequestDto;
import com.subject.flow.file.entity.FileType;
import com.subject.flow.file.repository.FileTypeRepository;
import com.subject.flow.file.service.FileTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FileTypeTest {

    @Mock
    FileTypeRepository fileTypeRepository;

    @InjectMocks
    FileTypeService fileTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("새로운 타입의 파일 생성")
    public void testSaveNewFileType() {
        FileRequestDto dto = new FileRequestDto("newType", true);
        FileType entity = FileType.builder()
                .name(dto.getFileType())
                .saved(dto.isSaved())
                .build();

        when(fileTypeRepository.findByName(dto.getFileType())).thenReturn(Optional.of(entity));
        when(fileTypeRepository.save(any(FileType.class))).thenReturn(entity);

        FileType result = fileTypeService.save(dto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(dto.getFileType());
        assertThat(result.isSaved()).isEqualTo(dto.isSaved());
        verify(fileTypeRepository).findByName(dto.getFileType());
        verify(fileTypeRepository).save(any(FileType.class));
    }
}
