package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.StatisticsDto;
import com.example.barkamol_avlod.entity.Statistics;
import com.example.barkamol_avlod.repository.StatisticsRepository;
import com.example.barkamol_avlod.service.StatisticsService;
import com.example.barkamol_avlod.service.maper.StatisticsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;
import static com.example.barkamol_avlod.status.AppStatusMessage.OK;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository repository;
    private final StatisticsMapper mapper;
    @Override
    public ResponseDto<StatisticsDto> update(StatisticsDto statisticsDto) {
        try {
            Optional<Statistics> byId = repository.findById(1);

            if (byId.isEmpty()){
                repository.save(mapper.toEntity(statisticsDto));
            }

            Statistics statistics = byId.get();

            statistics.setNumberOfStudents(statisticsDto.getNumberOfStudents() != null ? statisticsDto.getNumberOfStudents() : statistics.getNumberOfStudents());
            statistics.setRatioToTheNumberOfStudents(statisticsDto.getRatioToTheNumberOfStudents() != null ? statisticsDto.getRatioToTheNumberOfStudents() : statistics.getRatioToTheNumberOfStudents());
            statistics.setNumberOfEmployees(statisticsDto.getNumberOfEmployees() != null ? statisticsDto.getNumberOfEmployees() : statistics.getNumberOfEmployees());
            statistics.setNumberOfDirections(statisticsDto.getNumberOfDirections() != null ? statisticsDto.getNumberOfDirections() : statistics.getNumberOfDirections());

            repository.save(statistics);

            return ResponseDto.<StatisticsDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(statistics))
                    .build();
        } catch (Exception e){
            return ResponseDto.<StatisticsDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }


    @Override
    public ResponseDto<StatisticsDto> get() {
        try {
            Optional<Statistics> byId = repository.findById(1);
            if (byId.isEmpty()){
                return ResponseDto.<StatisticsDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }
            return ResponseDto.<StatisticsDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<StatisticsDto>builder()
                    .message(e.getMessage())
                    .build();
        }
    }
}