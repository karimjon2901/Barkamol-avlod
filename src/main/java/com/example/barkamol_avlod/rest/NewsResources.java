package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.NewsDto;
import com.example.barkamol_avlod.dto.NewsInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsResources {
    private final NewsService service;

    @Operation(
            method = "Add new news",
            description = "Need to send NewsDto to this endpoint to create new news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Add"
    )
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<NewsDto> add(@ModelAttribute NewsInputDto newsInputDto){
        return service.add(newsInputDto);
    }

    @Operation(
            method = "Get news by id",
            description = "Need to send id to this endpoint to get news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "application/json")),
            summary = "By-id"
    )
    @GetMapping("/{id}")
    public ResponseDto<NewsDto> getById(@PathVariable String id){
        return service.getById(id);
    }

    @Operation(
            method = "Update news",
            description = "Need to send NewsDto to this endpoint to update news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Update"
    )
    @PatchMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<NewsDto> update(@ModelAttribute NewsInputDto newsInputDto){
        return service.update(newsInputDto);
    }

    @Operation(
            method = "Delete news",
            description = "Need to send news id to this endpoint to delete this news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "application/json")),
            summary = "Delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<NewsDto> delete(@PathVariable String id){
        return service.delete(id);
    }

    @Operation(
            method = "Get all news",
            description = "This endpoint return all news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get all"
    )
    @GetMapping
    public ResponseDto<List<NewsDto>> getAll(){
        return service.getAll();
    }
}