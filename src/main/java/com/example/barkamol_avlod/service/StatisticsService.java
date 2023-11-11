package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.StatisticsDto;

public interface StatisticsService {
    ResponseDto<StatisticsDto> update(StatisticsDto statisticsDto);

    ResponseDto<StatisticsDto> get();
}
