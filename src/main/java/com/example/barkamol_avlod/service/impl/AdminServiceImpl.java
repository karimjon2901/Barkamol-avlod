package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.config.JwtService;
import com.example.barkamol_avlod.dto.AdminDto;
import com.example.barkamol_avlod.dto.LoginDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.entity.Admin;
import com.example.barkamol_avlod.repository.AdminRepository;
import com.example.barkamol_avlod.service.AdminService;
import com.example.barkamol_avlod.service.maper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    @Override
    public ResponseDto<AdminDto> update(AdminDto adminDto) {
        if (adminDto.getId() == null){
            return ResponseDto.<AdminDto>builder()
                    .message(NULL_VALUE)
                    .data(adminDto)
                    .build();
        }
        try {
            Optional<Admin> userOptional = adminRepository.findById(adminDto.getId());

            if (userOptional.isEmpty()){
                return ResponseDto.<AdminDto>builder()
                        .message(NOT_FOUND)
                        .data(adminDto)
                        .build();
            }
            Admin user = userOptional.get();

            user.setPassword(adminDto.getPassword() != null ? adminDto.getPassword() : user.getPassword());

            if (adminDto.getUsername() != null) {
                Optional<Admin> user1 = adminRepository.findFirstByUsername(adminDto.getUsername());
                if (user1.isEmpty()){
                    user.setUsername(adminDto.getUsername());
                }
                else {
                    return ResponseDto.<AdminDto>builder()
                            .message("This username already exists!")
                            .build();
                }
            }

            adminRepository.save(user);

            return ResponseDto.<AdminDto>builder()
                    .data(adminMapper.toDto(user))
                    .success(true)
                    .message(OK)
                    .build();
        }catch (Exception e){
            return ResponseDto.<AdminDto>builder()
                    .message(DATABASE_ERROR + e.getMessage())
                    .build();
        }
    }
    @Override
    public ResponseDto<String> loginUser(LoginDto loginDto) {
        Admin admin = loadUserByUsername(loginDto.getUsername());
        if (!encoder.matches(loginDto.getPassword(), admin.getPassword())){
            return ResponseDto.<String>builder()
                    .message("Password is not correct "+ admin.getPassword())
                    .build();
        }

        return ResponseDto.<String>builder()
                .success(true)
                .message(OK)
                .data(jwtService.generateToken(admin))
                .build();
    }

    private Admin loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> users = adminRepository.findFirstByUsername(username);
        if (users.isEmpty()) throw new UsernameNotFoundException("User with username : " + username + " is not found");
        return users.get();
    }
}