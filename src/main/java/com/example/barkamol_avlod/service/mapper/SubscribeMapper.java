package com.example.barkamol_avlod.service.mapper;

import com.example.barkamol_avlod.dto.SubscribeDto;
import com.example.barkamol_avlod.entity.Subscribe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SubscribeMapper implements CommonMapper<SubscribeDto, Subscribe>{
    @Override
    public abstract SubscribeDto toDto(Subscribe subscribe);

    @Override
    public abstract Subscribe toEntity(SubscribeDto subscribeDto);
}
