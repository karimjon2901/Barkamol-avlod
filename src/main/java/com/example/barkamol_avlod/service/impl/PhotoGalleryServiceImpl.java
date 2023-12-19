package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.config.S3File;
import com.example.barkamol_avlod.dto.ImageDto;
import com.example.barkamol_avlod.dto.PhotoGalleryDto;
import com.example.barkamol_avlod.dto.PhotoGalleryInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.entity.Image;
import com.example.barkamol_avlod.entity.PhotoGallery;
import com.example.barkamol_avlod.repository.ImageRepository;
import com.example.barkamol_avlod.repository.PhotoGalleryRepository;
import com.example.barkamol_avlod.service.IdGenerator;
import com.example.barkamol_avlod.service.PhotoGalleryService;
import com.example.barkamol_avlod.service.mapper.ImageMapper;
import com.example.barkamol_avlod.service.mapper.PhotoGalleryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class PhotoGalleryServiceImpl implements PhotoGalleryService {
    private final S3File s3File;
    private final PhotoGalleryRepository photoGalleryRepository;
    private final PhotoGalleryMapper photoGalleryMapper;
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final IdGenerator idGenerator;
    @Override
    public ResponseDto<PhotoGalleryDto> add(PhotoGalleryInputDto photoGalleryInputDto) {
        try {
            PhotoGallery entity = new PhotoGallery();
            List<Image> imageList = new ArrayList<>();

            for (MultipartFile image : photoGalleryInputDto.getImages()) {
                ImageDto imageDto1 = new ImageDto(s3File.postFile(image));
                Image i = imageMapper.toEntity(imageDto1);
                imageRepository.save(i);
                imageList.add(i);
            }
            entity.setId(idGenerator.generate());
            entity.setImages(imageList);
            entity.setTitleUZ(photoGalleryInputDto.getTitleUZ());
            entity.setTitleRU(photoGalleryInputDto.getTitleRU());
            entity.setTitleEN(photoGalleryInputDto.getTitleEN());


            photoGalleryRepository.save(entity);

            return ResponseDto.<PhotoGalleryDto>builder()
                    .success(true)
                    .message(OK)
                    .data(photoGalleryMapper.toDto(entity))
                    .build();
        } catch (Exception e){
            return ResponseDto.<PhotoGalleryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<PhotoGalleryDto>> getAll() {
        try {
            return ResponseDto.<List<PhotoGalleryDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(photoGalleryRepository.findAll().stream().map(photoGalleryMapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<PhotoGalleryDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<PhotoGalleryDto> getById(String id) {
        if (id == null){
            return ResponseDto.<PhotoGalleryDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<PhotoGallery> byId = photoGalleryRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<PhotoGalleryDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<PhotoGalleryDto>builder()
                    .success(true)
                    .message(OK)
                    .data(photoGalleryMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<PhotoGalleryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<PhotoGalleryDto> delete(String id) {
        if (id == null){
            return ResponseDto.<PhotoGalleryDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<PhotoGallery> byId = photoGalleryRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<PhotoGalleryDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            photoGalleryRepository.deleteById(id);

            return ResponseDto.<PhotoGalleryDto>builder()
                    .message(OK)
                    .success(true)
                    .data(photoGalleryMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<PhotoGalleryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<PhotoGalleryDto> update(PhotoGalleryInputDto photoGalleryInputDto) {
        if (photoGalleryInputDto.getId() == null){
            return ResponseDto.<PhotoGalleryDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<PhotoGallery> byId = photoGalleryRepository.findById(photoGalleryInputDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<PhotoGalleryDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            PhotoGallery entity = byId.get();

            entity.setTitleUZ(photoGalleryInputDto.getTitleUZ() != null ? photoGalleryInputDto.getTitleUZ() : entity.getTitleUZ());
            entity.setTitleRU(photoGalleryInputDto.getTitleRU() != null ? photoGalleryInputDto.getTitleRU() : entity.getTitleRU());
            entity.setTitleEN(photoGalleryInputDto.getTitleEN() != null ? photoGalleryInputDto.getTitleEN() : entity.getTitleEN());

            if (photoGalleryInputDto.getImages() != null){
                List<Image> images = new ArrayList<>();

                for (MultipartFile image : photoGalleryInputDto.getImages()) {
                    ImageDto imageDto1 = new ImageDto(s3File.postFile(image));
                    Image i = imageMapper.toEntity(imageDto1);
                    imageRepository.save(i);
                    images.add(i);
                }

                entity.setImages(images);
            }

            photoGalleryRepository.save(entity);

            return ResponseDto.<PhotoGalleryDto>builder()
                    .success(true)
                    .message(OK)
                    .data(photoGalleryMapper.toDto(entity))
                    .build();
        } catch (Exception e){
            return ResponseDto.<PhotoGalleryDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
