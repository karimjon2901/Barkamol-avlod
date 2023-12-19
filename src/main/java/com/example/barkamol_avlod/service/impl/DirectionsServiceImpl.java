package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.config.S3File;
import com.example.barkamol_avlod.dto.DirectionsDto;
import com.example.barkamol_avlod.dto.DirectionsInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.entity.Directions;
import com.example.barkamol_avlod.repository.CategoryRepository;
import com.example.barkamol_avlod.repository.DirectionsRepository;
import com.example.barkamol_avlod.service.DirectionsService;
import com.example.barkamol_avlod.service.IdGenerator;
import com.example.barkamol_avlod.service.mapper.CategoryMapper;
import com.example.barkamol_avlod.service.mapper.DirectionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class DirectionsServiceImpl implements DirectionsService {
    private final DirectionsRepository repository;
    private final DirectionsMapper mapper;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final S3File s3File;
    private final IdGenerator idGenerator;

    @Override
    public ResponseDto<DirectionsDto> add(DirectionsInputDto directionsInputDto) {
        try {
            DirectionsDto directionsDto = new DirectionsDto();

            directionsDto.setId(idGenerator.generate());
            directionsDto.setCategory(categoryMapper.toDto(categoryRepository.findById(directionsInputDto.getCategory()).get()));
            directionsDto.setImage(s3File.postFile(directionsInputDto.getImage()));
            directionsDto.setNameRU(directionsInputDto.getNameRU());
            directionsDto.setNameUZ(directionsInputDto.getNameUZ());
            directionsDto.setNameEN(directionsInputDto.getNameEN());
            directionsDto.setDescriptionRU(directionsInputDto.getDescriptionRU());
            directionsDto.setDescriptionUZ(directionsInputDto.getDescriptionUZ());
            directionsDto.setDescriptionEN(directionsInputDto.getDescriptionEN());

            repository.save(mapper.toEntity(directionsDto));

            return ResponseDto.<DirectionsDto>builder()
                    .message(OK)
                    .success(true)
                    .data(directionsDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<DirectionsDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<DirectionsDto> getById(String id) {
        if (id == null){
            return ResponseDto.<DirectionsDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Directions> byId = repository.findById(id);
            if (byId.isEmpty()){
                return ResponseDto.<DirectionsDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<DirectionsDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<DirectionsDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<DirectionsDto>> getAll() {
        try {
            return ResponseDto.<List<DirectionsDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(repository.findAll().stream().map(mapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<DirectionsDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<DirectionsDto> update(DirectionsInputDto directionsInputDto) {
        if (directionsInputDto.getId() == null){
            return ResponseDto.<DirectionsDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Directions> byId = repository.findById(directionsInputDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<DirectionsDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            Directions directions = byId.get();

            if (directionsInputDto.getImage() != null){
                directions.setImage(s3File.postFile(directionsInputDto.getImage()));
            }

            directions.setNameRU(directionsInputDto.getNameRU() != null ? directionsInputDto.getNameRU() : directions.getNameRU());
            directions.setNameUZ(directionsInputDto.getNameUZ() != null ? directionsInputDto.getNameUZ() : directions.getNameUZ());
            directions.setNameEN(directionsInputDto.getNameEN() != null ? directionsInputDto.getNameEN() : directions.getNameEN());

            directions.setDescriptionRU(directionsInputDto.getDescriptionRU() != null ? directionsInputDto.getDescriptionRU() : directions.getDescriptionRU());
            directions.setDescriptionUZ(directionsInputDto.getDescriptionUZ() != null ? directionsInputDto.getDescriptionUZ() : directions.getDescriptionUZ());
            directions.setDescriptionEN(directionsInputDto.getDescriptionEN() != null ? directionsInputDto.getDescriptionEN() : directions.getDescriptionEN());

            repository.save(directions);

            return ResponseDto.<DirectionsDto>builder()
                    .message(OK)
                    .success(true)
                    .data(mapper.toDto(directions))
                    .build();
        } catch (Exception e){
            return ResponseDto.<DirectionsDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<DirectionsDto> delete(String id) {
        if (id == null){
            return ResponseDto.<DirectionsDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Directions> byId = repository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<DirectionsDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            repository.deleteById(id);

            return ResponseDto.<DirectionsDto>builder()
                    .message(OK)
                    .success(true)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<DirectionsDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<DirectionsDto>> getByCategoryId(String categoryId) {
        if (categoryId == null){
            return ResponseDto.<List<DirectionsDto>>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            List<Directions> byId = repository.findAllByCategoryId(categoryId);
            if (byId.isEmpty()){
                return ResponseDto.<List<DirectionsDto>>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<List<DirectionsDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(byId.stream().map(mapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<DirectionsDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
