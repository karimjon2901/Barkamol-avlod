package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.CategoryDto;
import com.example.barkamol_avlod.dto.ResponseDto;

import java.util.List;

public interface CategoryService {

    ResponseDto<CategoryDto> getById(Integer id);

    ResponseDto<List<CategoryDto>> getAll();

    ResponseDto<CategoryDto> addCategory(CategoryDto categoryDto);

    ResponseDto<Void> delete(Integer id);

    ResponseDto<CategoryDto> update(CategoryDto categoryDto);
}
