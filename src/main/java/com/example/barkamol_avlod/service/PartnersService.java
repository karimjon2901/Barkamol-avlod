package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.PartnersDto;
import com.example.barkamol_avlod.dto.PartnersInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;

import java.util.List;

public interface PartnersService {
    ResponseDto<PartnersDto> add(PartnersInputDto partnersInputDto);

    ResponseDto<List<PartnersDto>> getAll();

    ResponseDto<PartnersDto> update(PartnersInputDto partnersInputDto);

    ResponseDto<PartnersDto> delete(String id);
}
