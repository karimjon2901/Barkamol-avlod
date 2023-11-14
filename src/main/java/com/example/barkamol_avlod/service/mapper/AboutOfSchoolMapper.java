package com.example.barkamol_avlod.service.mapper;

import com.example.barkamol_avlod.dto.AboutOfSchoolDto;
import com.example.barkamol_avlod.entity.AboutOfSchool;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AboutOfSchoolMapper extends CommonMapper<AboutOfSchoolDto, AboutOfSchool>{
}
