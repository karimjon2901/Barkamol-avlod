package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.DirectionsDto;
import com.example.barkamol_avlod.dto.DirectionsInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.service.DirectionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directions")
@RequiredArgsConstructor
public class DirectionsResources {
    private final DirectionsService service;

    @Operation(
            method = "Add new direction",
            description = "Need to send DirectionsDto to this endpoint to create new direction",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Direction info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "add"
    )
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<DirectionsDto> add(@ModelAttribute DirectionsInputDto directionsInputDto){
        return service.add(directionsInputDto);
    }

    @Operation(
            method = "Get direction by id",
            description = "Need to send id to this endpoint to get direction",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Direction info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get by id"
    )
    @GetMapping("/by-id/{id}")
    public ResponseDto<DirectionsDto> getById(@PathVariable String id){
        return service.getById(id);
    }

    @Operation(
            method = "Get all directions",
            description = "This endpoint return all directions",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Direction info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get all"
    )
    @GetMapping
    public ResponseDto<List<DirectionsDto>> getAll(){
        return service.getAll();
    }

    @Operation(
            method = "Update direction",
            description = "Need to send DirectionDto to this endpoint to update direction",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Direction info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Update"
    )
    @PatchMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<DirectionsDto> update(@ModelAttribute DirectionsInputDto directionsInputDto){
        return service.update(directionsInputDto);
    }

    @Operation(
            method = "Delete direction",
            description = "Need to send direction id to this endpoint to delete this direction",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Direction info",
                    content = @Content(mediaType = "application/json")),
            summary = "Delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<DirectionsDto> delete(@PathVariable String id){
        return service.delete(id);
    }

    @Operation(
            method = "Get all directions by category id",
            description = "This endpoint return all directions by category id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Direction info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get by category id"
    )
    @GetMapping("/by-category/{id}")
    public ResponseDto<List<DirectionsDto>> byCategoryId(@PathVariable String id){
        return service.getByCategoryId(id);
    }
}
