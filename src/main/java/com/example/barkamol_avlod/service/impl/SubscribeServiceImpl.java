package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.SubscribeDto;
import com.example.barkamol_avlod.repository.SubscribeRepository;
import com.example.barkamol_avlod.service.IdGenerator;
import com.example.barkamol_avlod.service.SubscribeService;
import com.example.barkamol_avlod.service.mapper.SubscribeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.barkamol_avlod.status.AppStatusMessage.DATABASE_ERROR;
import static com.example.barkamol_avlod.status.AppStatusMessage.OK;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {
    private final SubscribeRepository repository;
    private final SubscribeMapper mapper;
    private final IdGenerator idGenerator;

    @Override
    public ResponseDto<SubscribeDto> add(SubscribeDto subscribeDto) {
        try {
            subscribeDto.setId(idGenerator.generate());
            repository.save(mapper.toEntity(subscribeDto));

            return ResponseDto.<SubscribeDto>builder()
                    .message(OK)
                    .success(true)
                    .data(subscribeDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<SubscribeDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<SubscribeDto>> getAll() {
        try {
            return ResponseDto.<List<SubscribeDto>>builder()
                    .message(OK)
                    .success(true)
                    .data(repository.findAll().stream().map(mapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<SubscribeDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
