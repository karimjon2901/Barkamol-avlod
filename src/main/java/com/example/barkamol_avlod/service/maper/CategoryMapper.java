package com.example.barkamol_avlod.service.maper;

import com.example.barkamol_avlod.dto.CategoryDto;
import com.example.barkamol_avlod.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper implements CommonMapper<CategoryDto, Category>{
    @Override
    public abstract CategoryDto toDto(Category category);

    @Override
    public abstract Category toEntity(CategoryDto categoryDto);
}