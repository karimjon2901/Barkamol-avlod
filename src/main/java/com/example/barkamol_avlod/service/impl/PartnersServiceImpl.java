package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.config.S3File;
import com.example.barkamol_avlod.dto.PartnersDto;
import com.example.barkamol_avlod.dto.PartnersInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.entity.Partners;
import com.example.barkamol_avlod.repository.PartnersRepository;
import com.example.barkamol_avlod.service.PartnersService;
import com.example.barkamol_avlod.service.maper.PartnersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class PartnersServiceImpl implements PartnersService {
    private final PartnersRepository repository;
    private final PartnersMapper mapper;
    private final S3File s3File;

    @Override
    public ResponseDto<PartnersDto> add(PartnersInputDto partnersInputDto) {
        try {
            PartnersDto partnersDto = new PartnersDto();
            partnersDto.setImage(s3File.postFile(partnersInputDto.getImage()));
            partnersDto.setNameRU(partnersInputDto.getNameRU());
            partnersDto.setNameUZ(partnersInputDto.getNameUZ());
            partnersDto.setNameEN(partnersInputDto.getNameEN());

            repository.save(mapper.toEntity(partnersDto));

            return ResponseDto.<PartnersDto>builder()
                    .message(OK)
                    .success(true)
                    .data(partnersDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<PartnersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<PartnersDto>> getAll() {
        try {
            return ResponseDto.<List<PartnersDto>>builder()
                    .message(OK)
                    .success(true)
                    .data(repository.findAll().stream().map(mapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<PartnersDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<PartnersDto> update(PartnersInputDto partnersInputDto) {
        if (partnersInputDto.getId() == null){
            return ResponseDto.<PartnersDto>builder()
                    .message(NULL_ID)
                    .build();
        }

        try {
            Optional<Partners> byId = repository.findById(partnersInputDto.getId());
            if (byId.isEmpty()){
                return ResponseDto.<PartnersDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            Partners partners = byId.get();

            if (partnersInputDto.getImage() != null){
                partners.setImage(s3File.postFile(partnersInputDto.getImage()));
            }

            partners.setNameRU(partnersInputDto.getNameRU() != null ? partnersInputDto.getNameRU() : partners.getNameRU());
            partners.setNameUZ(partnersInputDto.getNameUZ() != null ? partnersInputDto.getNameUZ() : partners.getNameUZ());
            partners.setNameEN(partnersInputDto.getNameEN() != null ? partnersInputDto.getNameEN() : partners.getNameEN());

            repository.save(partners);

            return ResponseDto.<PartnersDto>builder()
                    .message(OK)
                    .success(true)
                    .data(mapper.toDto(partners))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<PartnersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<PartnersDto> delete(Integer id) {
        if (id == null){
            return ResponseDto.<PartnersDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Partners> byId = repository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<PartnersDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            repository.deleteById(id);
            return ResponseDto.<PartnersDto>builder()
                    .message(OK)
                    .success(true)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<PartnersDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
