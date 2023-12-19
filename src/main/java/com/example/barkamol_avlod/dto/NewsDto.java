package com.example.barkamol_avlod.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    private String id;
    private String image;

    private String titleRU;
    private String titleUZ;
    private String titleEN;

    private String subTitleRU;
    private String subTitleUZ;
    private String subTitleEN;

    private String descriptionRU;
    private String descriptionUZ;
    private String descriptionEN;

    private LocalDateTime createdAt;
}
