package com.subject.flow.file.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
