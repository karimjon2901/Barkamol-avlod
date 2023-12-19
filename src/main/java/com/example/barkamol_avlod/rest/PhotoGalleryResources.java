package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.PhotoGalleryDto;
import com.example.barkamol_avlod.dto.PhotoGalleryInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.service.PhotoGalleryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoGalleryResources {
    private final PhotoGalleryService service;

    @Operation(
            method = "Add new photo gallery",
            description = "Need to send PhotoGalleryDto to this endpoint to create new photo gallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Photo gallery info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Add"
    )
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<PhotoGalleryDto> add(@ModelAttribute PhotoGalleryInputDto photoGalleryInputDto){
        return service.add(photoGalleryInputDto);
    }

    @Operation(
            method = "Get all photo gallery",
            description = "This endpoint return all photo gallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Photo gallery info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get all"
    )
    @GetMapping
    public ResponseDto<List<PhotoGalleryDto>> getALl(){
        return service.getAll();
    }

    @Operation(
            method = "Get photo gallery by id",
            description = "Need to send id to this endpoint to get photo gallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Photo gallery info",
                    content = @Content(mediaType = "application/json")),
            summary = "By-id"
    )
    @GetMapping("/{id}")
    public ResponseDto<PhotoGalleryDto> getById(@PathVariable String id){
        return service.getById(id);
    }
    @Operation(
            method = "Update photo gallery",
            description = "Need to send PhotoGalleryDto to this endpoint to update photo gallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Photo gallery info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Update"
    )
    @PatchMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<PhotoGalleryDto> update(@ModelAttribute PhotoGalleryInputDto photoGalleryInputDto){
        return service.update(photoGalleryInputDto);
    }

    @Operation(
            method = "Delete photo gallery",
            description = "Need to send photo gallery id to this endpoint to delete this photo gallery",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Photo gallery info",
                    content = @Content(mediaType = "application/json")),
            summary = "Delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<PhotoGalleryDto> delete(@PathVariable String id){
        return service.delete(id);
    }
}
