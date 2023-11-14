package com.example.barkamol_avlod.service.mapper;

import com.example.barkamol_avlod.dto.VideoGalleryDto;
import com.example.barkamol_avlod.entity.VideoGallery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class VideoGalleryMapper implements CommonMapper<VideoGalleryDto, VideoGallery>{
    @Override
    public abstract VideoGalleryDto toDto(VideoGallery videoGallery);

    @Override
    public abstract VideoGallery toEntity(VideoGalleryDto videoGalleryDto);
}
