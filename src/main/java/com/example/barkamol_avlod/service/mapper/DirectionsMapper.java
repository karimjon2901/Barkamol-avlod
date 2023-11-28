package com.example.barkamol_avlod.service.mapper;

import com.example.barkamol_avlod.dto.DirectionsDto;
import com.example.barkamol_avlod.entity.Directions;
import com.example.barkamol_avlod.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class DirectionsMapper implements CommonMapper<DirectionsDto, Directions>{
    @Autowired
    protected CategoryRepository repository;
    @Override
    public abstract DirectionsDto toDto(Directions directions);

    @Mapping(target = "category", expression = "java(repository.findById(directionsDto.getCategory().getId()).get())")
    @Override
    public abstract Directions toEntity(DirectionsDto directionsDto);
}