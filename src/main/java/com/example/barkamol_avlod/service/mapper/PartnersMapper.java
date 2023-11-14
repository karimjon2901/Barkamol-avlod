package com.example.barkamol_avlod.service.mapper;

import com.example.barkamol_avlod.dto.PartnersDto;
import com.example.barkamol_avlod.entity.Partners;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PartnersMapper implements CommonMapper<PartnersDto, Partners>{
    @Override
    public abstract PartnersDto toDto(Partners partners);

    @Override
    public abstract Partners toEntity(PartnersDto partnersDto);
}