package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.AboutOfSchoolDto;
import com.example.barkamol_avlod.dto.ResponseDto;

public interface AboutOfSchoolService {
    ResponseDto<AboutOfSchoolDto> update(AboutOfSchoolDto aboutOfSchoolDto);

    ResponseDto<AboutOfSchoolDto> get();
}
