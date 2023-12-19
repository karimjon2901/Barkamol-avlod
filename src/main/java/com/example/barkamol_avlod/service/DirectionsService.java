package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.DirectionsDto;
import com.example.barkamol_avlod.dto.DirectionsInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;

import java.util.List;

public interface DirectionsService {
    ResponseDto<DirectionsDto> add(DirectionsInputDto directionsInputDto);

    ResponseDto<DirectionsDto> getById(String id);

    ResponseDto<List<DirectionsDto>> getAll();

    ResponseDto<DirectionsDto> update(DirectionsInputDto directionsInputDto);

    ResponseDto<DirectionsDto> delete(String id);

    ResponseDto<List<DirectionsDto>> getByCategoryId(String categoryId);
}
