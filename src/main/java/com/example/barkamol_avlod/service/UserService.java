package com.example.barkamol_avlod.service;

import com.example.barkamol_avlod.dto.UserDto;
import com.example.barkamol_avlod.dto.LoginDto;
import com.example.barkamol_avlod.dto.ResponseDto;

public interface UserService {
    ResponseDto<UserDto> update(UserDto userDto);
    ResponseDto<String> loginUser(LoginDto loginDto);
}
