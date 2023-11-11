package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.AboutOfSchoolDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.service.AboutOfSchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/about")
@RequiredArgsConstructor
public class AboutOfSchoolResources {
    private final AboutOfSchoolService aboutOfSchoolService;
    @SecurityRequirement(name = "Authorization")
    @Operation(
            method = "Update about of school",
            description = "Need to send AboutOfSchoolDto to this endpoint to update",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "AboutOfSchool info",
                    content = @Content(mediaType = "application/json")),
            summary = "Update"
    )
    @PostMapping
    public ResponseDto<AboutOfSchoolDto> update(@Valid @RequestBody AboutOfSchoolDto aboutOfSchoolDto){
        return aboutOfSchoolService.update(aboutOfSchoolDto);
    }

    @Operation(
            method = "Get about of school",
            description = "This endpoint return aboutOfSchoolDto",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "AboutOfSchool info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get information"
    )
    @GetMapping
    public ResponseDto<AboutOfSchoolDto> get(){
        return aboutOfSchoolService.get();
    }
}
