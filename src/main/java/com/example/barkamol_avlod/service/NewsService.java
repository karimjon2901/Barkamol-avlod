package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.NewsDto;
import com.example.barkamol_avlod.dto.NewsInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;

import java.util.List;

public interface NewsService {
    ResponseDto<NewsDto> add(NewsInputDto newsInputDto);

    ResponseDto<NewsDto> getById(String id);

    ResponseDto<NewsDto> update(NewsInputDto newsInputDto);

    ResponseDto<NewsDto> delete(String id);

    ResponseDto<List<NewsDto>> getAll();
}
