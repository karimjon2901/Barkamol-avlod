package com.example.barkamol_avlod.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoGalleryDto {
    private Integer id;
    private String titleRU;
    private String titleUZ;
    private String titleEN;
    private List<ImageDto> images;
    private LocalDateTime createdAt;
}
