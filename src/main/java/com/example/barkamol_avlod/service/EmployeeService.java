package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.EmployeeDto;
import com.example.barkamol_avlod.dto.EmployeeInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;

import java.util.List;

public interface EmployeeService {
    ResponseDto<EmployeeDto> add(EmployeeInputDto employeeInputDto);

    ResponseDto<List<EmployeeDto>> getAll();

    ResponseDto<EmployeeDto> getById(Integer id);

    ResponseDto<EmployeeDto> update(EmployeeInputDto employeeInputDto);

    ResponseDto<EmployeeDto> delete(Integer id);

    ResponseDto<List<EmployeeDto>> getTeachers();

    ResponseDto<List<EmployeeDto>> getManagement();
}
