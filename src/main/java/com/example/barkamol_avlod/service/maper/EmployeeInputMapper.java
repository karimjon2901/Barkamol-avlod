package com.example.barkamol_avlod.service.maper;

import com.example.barkamol_avlod.config.S3File;
import com.example.barkamol_avlod.dto.EmployeeInputDto;
import com.example.barkamol_avlod.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EmployeeInputMapper{
    @Autowired
    protected S3File s3File;
    @Mapping(target = "image", expression = "java(s3File.postFile(employeeInputDto.getImage()))")
    public abstract EmployeeDto toDto(EmployeeInputDto employeeInputDto);
}
