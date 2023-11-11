package com.example.barkamol_avlod.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titleRU;
    private String titleUZ;
    private String titleEN;
    @OneToMany
    private List<Image> images;
    @CreatedDate
    private LocalDateTime createdAt;
}
