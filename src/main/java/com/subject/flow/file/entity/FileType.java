package com.subject.flow.file.entity;

import com.subject.flow.file.dto.FileRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FileType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private boolean saved;

    @Column
    private String name;

    public void saved(boolean saved) {
        this.saved = saved;
    }

    public static int customFileSize(List<FileRequestDto> list, List<String> excludeList){
        return list.stream().filter(element -> !excludeList.contains(element.getFileType())).toList().size();
    }
}
