package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.SubscribeDto;

import java.util.List;

public interface SubscribeService {
    ResponseDto<SubscribeDto> add(SubscribeDto subscribeDto);

    ResponseDto<List<SubscribeDto>> getAll();
}
