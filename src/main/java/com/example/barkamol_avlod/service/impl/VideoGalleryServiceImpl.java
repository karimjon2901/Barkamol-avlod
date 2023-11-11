package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.VideoGalleryDto;
import com.example.barkamol_avlod.entity.VideoGallery;
import com.example.barkamol_avlod.repository.VideoGalleryRepository;
import com.example.barkamol_avlod.service.VideoGalleryService;
import com.example.barkamol_avlod.service.maper.VideoGalleryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class VideoGalleryServiceImpl implements VideoGalleryService {
    private final VideoGalleryRepository repository;
    private final VideoGalleryMapper mapper;

    @Override
    public ResponseDto<VideoGalleryDto> add(VideoGalleryDto videoGalleryDto) {
        try {
            repository.save(mapper.toEntity(videoGalleryDto));
            return ResponseDto.<VideoGalleryDto>builder()
                    .message(OK)
                    .success(true)
                    .data(videoGalleryDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<VideoGalleryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<VideoGalleryDto>> getAll() {
        try {
            return ResponseDto.<List<VideoGalleryDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(repository.findAll().stream().map(mapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<VideoGalleryDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<VideoGalleryDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<VideoGalleryDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<VideoGallery> byId = repository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<VideoGalleryDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<VideoGalleryDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<VideoGalleryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<VideoGalleryDto> update(VideoGalleryDto videoGalleryDto) {
        if (videoGalleryDto.getId() == null){
            return ResponseDto.<VideoGalleryDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<VideoGallery> byId = repository.findById(videoGalleryDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<VideoGalleryDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            VideoGallery videoGallery = byId.get();

            videoGallery.setUrl(videoGalleryDto.getUrl() != null ? videoGalleryDto.getUrl() : videoGallery.getUrl());
            videoGallery.setTitleRU(videoGalleryDto.getTitleRU() != null ? videoGalleryDto.getTitleRU() : videoGallery.getTitleRU());
            videoGallery.setTitleUZ(videoGalleryDto.getTitleUZ() != null ? videoGalleryDto.getTitleUZ() : videoGallery.getTitleUZ());
            videoGallery.setTitleEN(videoGalleryDto.getTitleEN() != null ? videoGalleryDto.getTitleEN() : videoGallery.getTitleEN());

            repository.save(videoGallery);

            return ResponseDto.<VideoGalleryDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(videoGallery))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<VideoGalleryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<VideoGalleryDto> delete(Integer id) {
        if (id == null){
            return ResponseDto.<VideoGalleryDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<VideoGallery> byId = repository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<VideoGalleryDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            repository.deleteById(id);

            return ResponseDto.<VideoGalleryDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<VideoGalleryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
