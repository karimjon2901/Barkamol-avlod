package com.example.barkamol_avlod.service.mapper;

import com.example.barkamol_avlod.dto.PhotoGalleryDto;
import com.example.barkamol_avlod.entity.PhotoGallery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PhotoGalleryMapper implements CommonMapper<PhotoGalleryDto, PhotoGallery>{
    @Override
    public abstract PhotoGalleryDto toDto(PhotoGallery photoGallery);

    @Override
    public abstract PhotoGallery toEntity(PhotoGalleryDto photoGalleryDto);
}
