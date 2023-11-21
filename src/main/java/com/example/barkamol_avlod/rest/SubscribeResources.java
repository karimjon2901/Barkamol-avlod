package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.SubscribeDto;
import com.example.barkamol_avlod.service.SubscribeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscribe")
@RequiredArgsConstructor
public class SubscribeResources {
    private final SubscribeService service;

    @Operation(
            method = "Add statistics",
            description = "Need to send SubscribeDto to this endpoint to add",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Subscribe info",
                    content = @Content(mediaType = "application/json")),
            summary = "Add"
    )
    @PostMapping
    public ResponseDto<SubscribeDto> add(@RequestBody SubscribeDto subscribeDto){
        return service.add(subscribeDto);
    }@Operation(
            method = "Get all subscribes",
            description = "This endpoint return all subscribeDto",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Subscribe info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get all"
    )
    @GetMapping
    public ResponseDto<List<SubscribeDto>> getAll(){
        return service.getAll();
    }
}
