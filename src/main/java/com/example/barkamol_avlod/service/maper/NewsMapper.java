package com.example.barkamol_avlod.service.maper;

import com.example.barkamol_avlod.dto.NewsDto;
import com.example.barkamol_avlod.entity.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper extends CommonMapper<NewsDto, News>{
}
