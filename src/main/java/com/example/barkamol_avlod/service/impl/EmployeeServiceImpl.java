package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.dto.EmployeeDto;
import com.example.barkamol_avlod.dto.EmployeeInputDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.entity.Employee;
import com.example.barkamol_avlod.repository.EmployeeRepository;
import com.example.barkamol_avlod.service.EmployeeService;
import com.example.barkamol_avlod.service.maper.EmployeeInputMapper;
import com.example.barkamol_avlod.service.maper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final EmployeeInputMapper employeeInputMapper;

    @Override
    public ResponseDto<EmployeeDto> add(EmployeeInputDto employeeInputDto) {
        try {
            EmployeeDto employeeDto = employeeInputMapper.toDto(employeeInputDto);
            repository.save(mapper.toEntity(employeeDto));

            return ResponseDto.<EmployeeDto>builder()
                    .success(true)
                    .message(OK)
                    .data(employeeDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<EmployeeDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<EmployeeDto>> getAll() {
        try {
            return ResponseDto.<List<EmployeeDto>>builder()
                    .message(OK)
                    .success(true)
                    .data(repository.findAll().stream().map(mapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<EmployeeDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<EmployeeDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<EmployeeDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Employee> byId = repository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<EmployeeDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<EmployeeDto>builder()
                    .message(OK)
                    .success(true)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<EmployeeDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<EmployeeDto> update(EmployeeInputDto employeeInputDto) {
        if (employeeInputDto.getId() == null){
            return ResponseDto.<EmployeeDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Employee> byId = repository.findById(employeeInputDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<EmployeeDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            EmployeeDto employeeDto = employeeInputMapper.toDto(employeeInputDto);
            Employee employee = byId.get();

            employee.setImage(employeeDto.getImage() != null ? employeeDto.getImage() : employee.getImage());
            employee.setEmail(employeeDto.getEmail() != null ? employeeDto.getEmail() : employee.getEmail());
            employee.setInstagram(employeeDto.getInstagram() != null ? employeeDto.getInstagram() : employee.getInstagram());
            employee.setFacebook(employeeDto.getFacebook() != null ? employeeDto.getFacebook() : employee.getFacebook());
            employee.setTelegram(employeeDto.getTelegram() != null ? employeeDto.getTelegram() : employee.getTelegram());
            employee.setPhoneNumber(employeeDto.getPhoneNumber() != null ? employeeDto.getPhoneNumber() : employee.getPhoneNumber());
            employee.setRole(employeeDto.getRole() != null ? employeeDto.getRole() : employee.getRole());
            employee.setNameRU(employeeDto.getNameRU() != null ? employeeDto.getNameRU() : employee.getNameRU());
            employee.setNameUZ(employeeDto.getNameUZ() != null ? employeeDto.getNameUZ() : employee.getNameUZ());
            employee.setNameEN(employeeDto.getNameEN() != null ? employeeDto.getNameEN() : employee.getNameEN());
            employee.setPositionRU(employeeDto.getPositionRU() != null ? employeeDto.getPositionRU() : employee.getPositionRU());
            employee.setPositionUZ(employeeDto.getPositionUZ() != null ? employeeDto.getPositionUZ() : employee.getPositionUZ());
            employee.setPositionEN(employeeDto.getPositionEN() != null ? employeeDto.getPositionEN() : employee.getPositionEN());

            repository.save(employee);

            return ResponseDto.<EmployeeDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(employee))
                    .build();
        } catch (Exception e){
            return ResponseDto.<EmployeeDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<EmployeeDto> delete(Integer id) {
        if (id == null){
            return ResponseDto.<EmployeeDto>builder()
                    .message(NULL_ID)
                    .build();
        }
        try {
            Optional<Employee> byId = repository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<EmployeeDto>builder()
                        .message(NOT_FOUND)
                        .build();
            }

            repository.deleteById(id);

            return ResponseDto.<EmployeeDto>builder()
                    .success(true)
                    .message(OK)
                    .data(mapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<EmployeeDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<EmployeeDto>> getTeachers() {
        try {
            return ResponseDto.<List<EmployeeDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(repository.findAllByRole("TEACHER").stream().map(mapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<EmployeeDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<EmployeeDto>> getManagement() {
        try {
            return ResponseDto.<List<EmployeeDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(repository.findAllByRole("MANAGEMENT").stream().map(mapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<EmployeeDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
