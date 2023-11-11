package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.AdminDto;
import com.example.barkamol_avlod.dto.LoginDto;
import com.example.barkamol_avlod.dto.ResponseDto;

public interface AdminService {
    ResponseDto<AdminDto> update(AdminDto adminDto);
    ResponseDto<String> loginUser(LoginDto loginDto);
}
