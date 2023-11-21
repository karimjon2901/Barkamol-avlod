package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.StatisticsDto;
import com.example.barkamol_avlod.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsResources {
    private final StatisticsService service;

    @Operation(
            method = "Update statistics",
            description = "Need to send StatisticsDto to this endpoint to update",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Statistics info",
                    content = @Content(mediaType = "application/json")),
            summary = "Update"
    )
    @PostMapping
    public ResponseDto<StatisticsDto> update(@RequestBody StatisticsDto statisticsDto){
        return service.update(statisticsDto);
    }

    @Operation(
            method = "Get statistics",
            description = "This endpoint return statisticsDto",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Statistics info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get information"
    )
    @GetMapping
    public ResponseDto<StatisticsDto> get(){
        return service.get();
    }
}
