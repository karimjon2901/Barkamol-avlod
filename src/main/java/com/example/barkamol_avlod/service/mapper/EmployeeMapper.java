package com.example.barkamol_avlod.service.mapper;

import com.example.barkamol_avlod.dto.EmployeeDto;
import com.example.barkamol_avlod.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper implements CommonMapper<EmployeeDto, Employee>{
    @Override
    public abstract EmployeeDto toDto(Employee employee);

    @Override
    public abstract Employee toEntity(EmployeeDto employeeDto);
}
