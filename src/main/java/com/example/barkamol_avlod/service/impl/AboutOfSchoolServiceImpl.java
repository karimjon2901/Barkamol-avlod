package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.dto.AboutOfSchoolDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.entity.AboutOfSchool;
import com.example.barkamol_avlod.repository.AboutOfSchoolRepository;
import com.example.barkamol_avlod.service.AboutOfSchoolService;
import com.example.barkamol_avlod.service.maper.AboutOfSchoolMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class AboutOfSchoolServiceImpl implements AboutOfSchoolService {
    private final AboutOfSchoolRepository repository;
    private final AboutOfSchoolMapper mapper;

    @Override
    public ResponseDto<AboutOfSchoolDto> update(AboutOfSchoolDto aboutOfSchoolDto) {
        try {
            Optional<AboutOfSchool> byId = repository.findById(1);

            if (byId.isEmpty()){
                repository.save(mapper.toEntity(aboutOfSchoolDto));
            }

            AboutOfSchool aboutOfSchool = byId.get();
            aboutOfSchool.setTitleRU(aboutOfSchoolDto.getTitleRU() != null ? aboutOfSchoolDto.getTitleRU() : aboutOfSchool.getTitleRU());
            aboutOfSchool.setTitleUZ(aboutOfSchoolDto.getTitleUZ() != null ? aboutOfSchoolDto.getTitleUZ() : aboutOfSchool.getTitleUZ());
            aboutOfSchool.setTitleEN(aboutOfSchoolDto.getTitleEN() != null ? aboutOfSchoolDto.getTitleEN() : aboutOfSchool.getTitleEN());
            aboutOfSchool.setDescriptionRU(aboutOfSchoolDto.getDescriptionRU() != null? aboutOfSchoolDto.getDescriptionRU() : aboutOfSchool.getDescriptionRU());
            aboutOfSchool.setDescriptionUZ(aboutOfSchoolDto.getDescriptionUZ() != null? aboutOfSchoolDto.getDescriptionUZ() : aboutOfSchool.getDescriptionUZ());
            aboutOfSchool.setDescriptionEN(aboutOfSchoolDto.getDescriptionEN() != null? aboutOfSchoolDto.getDescriptionEN() : aboutOfSchool.getDescriptionEN());

            repository.save(aboutOfSchool);

            return ResponseDto.<AboutOfSchoolDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(aboutOfSchool))
                    .build();
        } catch (Exception e){
            return ResponseDto.<AboutOfSchoolDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }


    @Override
    public ResponseDto<AboutOfSchoolDto> get() {
        try {
            Optional<AboutOfSchool> byId = repository.findById(1);
            if (byId.isEmpty()){
                return ResponseDto.<AboutOfSchoolDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }
            return ResponseDto.<AboutOfSchoolDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<AboutOfSchoolDto>builder()
                    .message(e.getMessage())
                    .build();
        }
    }
}
