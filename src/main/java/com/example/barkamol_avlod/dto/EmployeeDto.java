package com.example.barkamol_avlod.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Integer id;
    private String image;
    private String nameRU;
    private String nameUZ;
    private String nameEN;
    private String positionRU;
    private String positionUZ;
    private String positionEN;
    private String phoneNumber;
    private String email;
    private String telegram;
    private String instagram;
    private String facebook;
    private String role;
}
