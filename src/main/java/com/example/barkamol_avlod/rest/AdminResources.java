package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.AdminDto;
import com.example.barkamol_avlod.dto.LoginDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminResources {
    private final AdminService adminService;
    @Operation(
            method = "Update admin",
            description = "Need to send AdminDto to this endpoint to update admin",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Admin info",
                    content = @Content(mediaType = "application/json"))
    )
    @SecurityRequirement(name = "Authorization")
    @PatchMapping
    public ResponseDto<AdminDto> updateUser(@Valid @RequestBody AdminDto adminDto){
        return adminService.update(adminDto);
    }

    @Operation(
            method = "Login admin",
            description = "Need to send username and password to this endpoint to login admin. You can get token.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Admin info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping("/login")
    public ResponseDto<String> loginUser(@Valid @RequestBody LoginDto loginDto) {
        return adminService.loginUser(loginDto);
    }
}
