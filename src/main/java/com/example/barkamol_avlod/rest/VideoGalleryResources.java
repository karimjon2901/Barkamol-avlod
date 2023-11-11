package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.dto.VideoGalleryDto;
import com.example.barkamol_avlod.service.VideoGalleryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoGalleryResources {
    private final VideoGalleryService service;

    @Operation(
            method = "Add new VideoGallery",
            description = "Need to send VideoGalleryDto to this endpoint to create new videoGallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "VideoGallery info",
                    content = @Content(mediaType = "application/json")),
            summary = "add"
    )
    @SecurityRequirement(name = "Authorization")
    @PostMapping
    public ResponseDto<VideoGalleryDto> add(@Valid @RequestBody VideoGalleryDto videoGalleryDto){
        return service.add(videoGalleryDto);
    }

    @Operation(
            method = "Get all VideoGallery",
            description = "This endpoint return all VideoGallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "VideoGallery info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get all"
    )
    @GetMapping
    public ResponseDto<List<VideoGalleryDto>> getAll(){
        return service.getAll();
    }

    @Operation(
            method = "Get VideoGallery by id",
            description = "Need to send id to this endpoint to get VideoGallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "VideoGallery info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get by id"
    )
    @GetMapping("/{id}")
    public ResponseDto<VideoGalleryDto> getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @Operation(
            method = "Update VideoGallery",
            description = "Need to send VideoGalleryDto to this endpoint to update VideoGallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "VideoGallery info",
                    content = @Content(mediaType = "application/json")),
            summary = "Update"
    )
    @SecurityRequirement(name = "Authorization")
    @PatchMapping
    public ResponseDto<VideoGalleryDto> update(@Valid @RequestBody VideoGalleryDto videoGalleryDto){
        return service.update(videoGalleryDto);
    }

    @Operation(
            method = "Delete VideoGallery",
            description = "Need to send VideoGallery id to this endpoint to delete this VideoGallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "VideoGallery info",
                    content = @Content(mediaType = "application/json")),
            summary = "Delete"
    )
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping("/{id}")
    public ResponseDto<VideoGalleryDto> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
