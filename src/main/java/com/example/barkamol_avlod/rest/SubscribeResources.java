package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.SubscribeDto;
import com.example.barkamol_avlod.service.SubscribeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscribe")
@RequiredArgsConstructor
public class SubscribeResources {
    private final SubscribeService service;


    @PostMapping
    public ResponseDto<SubscribeDto> add(@Valid @RequestBody SubscribeDto subscribeDto){
        return service.add(subscribeDto);
    }

    @SecurityRequirement(name = "Authorization")
    @GetMapping
    public ResponseDto<List<SubscribeDto>> getAll(){
        return service.getAll();
    }
}
