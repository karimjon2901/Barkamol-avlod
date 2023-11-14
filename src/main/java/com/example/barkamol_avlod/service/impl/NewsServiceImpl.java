package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.config.S3File;
import com.example.barkamol_avlod.dto.NewsDto;
import com.example.barkamol_avlod.dto.NewsInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.entity.News;
import com.example.barkamol_avlod.repository.NewsRepository;
import com.example.barkamol_avlod.service.NewsService;
import com.example.barkamol_avlod.service.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsMapper newsMapper;
    private final NewsRepository newsRepository;
    private final S3File s3File;
    @Override
    public ResponseDto<NewsDto> add(NewsInputDto newsInputDto) {
        NewsDto newsDto = new NewsDto();

        newsDto.setTitleRU(newsInputDto.getTitleRU());
        newsDto.setTitleUZ(newsInputDto.getTitleUZ());
        newsDto.setTitleEN(newsInputDto.getTitleEN());

        newsDto.setSubTitleRU(newsInputDto.getSubTitleRU());
        newsDto.setSubTitleUZ(newsInputDto.getSubTitleUZ());
        newsDto.setSubTitleEN(newsInputDto.getSubTitleEN());

        newsDto.setDescriptionRU(newsInputDto.getDescriptionRU());
        newsDto.setDescriptionUZ(newsInputDto.getDescriptionUZ());
        newsDto.setDescriptionEN(newsInputDto.getDescriptionEN());
        newsDto.setImage(s3File.postFile(newsInputDto.getImage()));

        try {
            newsRepository.save(newsMapper.toEntity(newsDto));
            return ResponseDto.<NewsDto>builder()
                    .success(true)
                    .message(OK)
                    .data(newsDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<NewsDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<NewsDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<NewsDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<News> byId = newsRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<NewsDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<NewsDto>builder()
                    .success(true)
                    .message(OK)
                    .data(newsMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<NewsDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<NewsDto> update(NewsInputDto newsInputDto) {
        if (newsInputDto.getId() == null){
            return ResponseDto.<NewsDto>builder()
                    .message(NULL_ID)
                    .build();
        }

        try {
            Optional<News> byId = newsRepository.findById(newsInputDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<NewsDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            News news = byId.get();

            news.setImage(newsInputDto.getImage() != null ? s3File.postFile(newsInputDto.getImage()) : news.getImage());
            news.setTitleRU(newsInputDto.getTitleRU() != null ? newsInputDto.getTitleRU() : news.getTitleRU());
            news.setTitleUZ(newsInputDto.getTitleUZ() != null ? newsInputDto.getTitleUZ() : news.getTitleUZ());
            news.setTitleEN(newsInputDto.getTitleEN() != null ? newsInputDto.getTitleEN() : news.getTitleEN());
            news.setSubTitleRU(newsInputDto.getSubTitleRU() != null ? newsInputDto.getSubTitleRU() : news.getSubTitleRU());
            news.setSubTitleUZ(newsInputDto.getSubTitleUZ() != null ? newsInputDto.getSubTitleUZ() : news.getSubTitleUZ());
            news.setSubTitleEN(newsInputDto.getSubTitleEN() != null ? newsInputDto.getSubTitleEN() : news.getSubTitleEN());
            news.setDescriptionRU(newsInputDto.getDescriptionRU() != null ? newsInputDto.getDescriptionRU() : news.getDescriptionRU());
            news.setDescriptionUZ(newsInputDto.getDescriptionUZ() != null ? newsInputDto.getDescriptionUZ() : news.getDescriptionUZ());
            news.setDescriptionEN(newsInputDto.getDescriptionEN() != null ? newsInputDto.getDescriptionEN() : news.getDescriptionEN());

            newsRepository.save(news);

            return ResponseDto.<NewsDto>builder()
                    .message(OK)
                    .success(true)
                    .data(newsMapper.toDto(news))
                    .build();
        } catch (Exception e){
            return ResponseDto.<NewsDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }


    }

    @Override
    public ResponseDto<NewsDto> delete(Integer id) {
        if (id == null) return ResponseDto.<NewsDto>builder()
                .message(NULL_ID)
                .build();
        try {
            Optional<News> newsOptional = newsRepository.findById(id);
            if (newsOptional.isEmpty()) {
                return ResponseDto.<NewsDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }
            NewsDto newsDto = newsMapper.toDto(newsOptional.get());
            newsRepository.delete(newsOptional.get());
            return ResponseDto.<NewsDto>builder()
                    .message(OK)
                    .success(true)
                    .data(newsDto)
                    .build();
        }catch (Exception e){
            return ResponseDto.<NewsDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<NewsDto>> getAll() {
        try {
            return ResponseDto.<List<NewsDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(newsRepository.getAllByOrderByCreatedAtDesc().stream().map(newsMapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<NewsDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}