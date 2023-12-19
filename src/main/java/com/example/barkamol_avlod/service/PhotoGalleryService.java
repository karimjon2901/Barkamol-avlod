package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.PhotoGalleryDto;
import com.example.barkamol_avlod.dto.PhotoGalleryInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;

import java.util.List;

public interface PhotoGalleryService {
    ResponseDto<PhotoGalleryDto> add(PhotoGalleryInputDto photoGalleryInputDto);

    ResponseDto<List<PhotoGalleryDto>> getAll();

    ResponseDto<PhotoGalleryDto> getById(String id);

    ResponseDto<PhotoGalleryDto> delete(String id);

    ResponseDto<PhotoGalleryDto> update(PhotoGalleryInputDto photoGalleryInputDto);
}
