package com.example.barkamol_avlod.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectionsDto {
    private String id;
    private CategoryDto category;
    private String image;
    private String nameRU;
    private String nameUZ;
    private String nameEN;
    private String descriptionRU;
    private String descriptionUZ;
    private String descriptionEN;
}
