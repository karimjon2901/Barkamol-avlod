package com.example.barkamol_avlod.service.mapper;

import com.example.barkamol_avlod.dto.ImageDto;
import com.example.barkamol_avlod.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper extends CommonMapper<ImageDto, Image>{
}
