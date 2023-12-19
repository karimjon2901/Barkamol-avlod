package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.VideoGalleryDto;

import java.util.List;

public interface VideoGalleryService {
    ResponseDto<VideoGalleryDto> add(VideoGalleryDto videoGalleryDto);

    ResponseDto<List<VideoGalleryDto>> getAll();

    ResponseDto<VideoGalleryDto> getById(String id);

    ResponseDto<VideoGalleryDto> update(VideoGalleryDto videoGalleryDto);

    ResponseDto<VideoGalleryDto> delete(String id);
}
