package com.example.barkamol_avlod.service.mapper;

import com.example.barkamol_avlod.dto.AdminDto;
import com.example.barkamol_avlod.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class AdminMapper implements CommonMapper<AdminDto, Admin>{
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Override
    public abstract AdminDto toDto(Admin admin);

    @Override
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(adminDto.getPassword()))")
    public abstract Admin toEntity(AdminDto adminDto);
}
