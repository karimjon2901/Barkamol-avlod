package com.example.barkamol_avlod.rest;

import com.example.barkamol_avlod.dto.EmployeeDto;
import com.example.barkamol_avlod.dto.EmployeeInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeResources {
    private final EmployeeService service;

    @Operation(
            method = "Add new employee",
            description = "Need to send EmployeeDto to this endpoint to create new employee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Employee info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Add"
    )
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<EmployeeDto> add(@ModelAttribute EmployeeInputDto employeeInputDto){
        return service.add(employeeInputDto);
    }

    @Operation(
            method = "Get all employees",
            description = "This endpoint return all employees",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Employee info",
                    content = @Content(mediaType = "application/json")),
            summary = "Get all"
    )
    @GetMapping
    public ResponseDto<List<EmployeeDto>> getAll(){
        return service.getAll();
    }

    @Operation(
            method = "Get employee by id",
            description = "Need to send id to this endpoint to get employee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Employee info",
                    content = @Content(mediaType = "application/json")),
            summary = "By-id"
    )
    @GetMapping("/{id}")
    public ResponseDto<EmployeeDto> getById(@PathVariable String id){
        return service.getById(id);
    }

    @Operation(
            method = "Update employee",
            description = "Need to send EmployeeDto to this endpoint to update employee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Employee info",
                    content = @Content(mediaType = "multipart/form-data")),
            summary = "Update"
    )
    @PatchMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<EmployeeDto> update(@ModelAttribute EmployeeInputDto employeeInputDto){
        return service.update(employeeInputDto);
    }

    @Operation(
            method = "Delete employee",
            description = "Need to send employee id to this endpoint to delete this employee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Employee info",
                    content = @Content(mediaType = "application/json")),
            summary = "Delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<EmployeeDto> delete(@PathVariable String id){
        return service.delete(id);
    }

    @GetMapping("/teachers")
    public ResponseDto<List<EmployeeDto>> getTeachers(){
        return service.getTeachers();
    }

    @GetMapping("/management")
    public ResponseDto<List<EmployeeDto>> getEmployees(){
        return service.getManagement();
    }
}
