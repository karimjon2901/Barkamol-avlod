package com.example.barkamol_avlod.service.maper;

import com.example.barkamol_avlod.dto.StatisticsDto;
import com.example.barkamol_avlod.entity.Statistics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticsMapper extends CommonMapper<StatisticsDto, Statistics>{
}
