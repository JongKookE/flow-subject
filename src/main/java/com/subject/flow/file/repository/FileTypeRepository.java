package com.subject.flow.file.repository;

import com.subject.flow.file.entity.FileType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileTypeRepository extends JpaRepository<FileType, Integer> {
    Optional<FileType> findByName(String name);
}
