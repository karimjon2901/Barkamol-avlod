package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.PartnersDto;
import com.example.barkamol_avlod.dto.PartnersInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.service.PartnersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners")
@RequiredArgsConstructor
public class PartnersResources {
    private final PartnersService service;
    @Operation(
            method = "Add new partner",
            description = "Need to send Part to this endpoint to create new news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Partners info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Add"
    )
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<PartnersDto> add(@ModelAttribute PartnersInputDto partnersInputDto){
        return service.add(partnersInputDto);
    }

    @Operation(
            method = "Get all partners",
            description = "This endpoint return all partners",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Partners info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get all"
    )
    @GetMapping
    public ResponseDto<List<PartnersDto>> getAll(){
        return service.getAll();
    }

    @Operation(
            method = "Update partners",
            description = "Need to send Partners to this endpoint to update partners",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Partners info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Update"
    )
    @PatchMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<PartnersDto> update(@ModelAttribute PartnersInputDto partnersInputDto){
        return service.update(partnersInputDto);
    }

    @Operation(
            method = "Delete partner",
            description = "Need to send partners id to this endpoint to delete this partners",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Partners info",
                    content = @Content(mediaType = "application/json")),
            summary = "Delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<PartnersDto> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
