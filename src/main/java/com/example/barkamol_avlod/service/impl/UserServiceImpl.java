package com.example.barkamol_avlod.service.impl;

import com.example.barkamol_avlod.config.JwtService;
import com.example.barkamol_avlod.dto.UserDto;
import com.example.barkamol_avlod.dto.LoginDto;
import com.example.barkamol_avlod.dto.ResponseDto;
import com.example.barkamol_avlod.entity.Users;
import com.example.barkamol_avlod.repository.UserRepository;
import com.example.barkamol_avlod.service.IdGenerator;
import com.example.barkamol_avlod.service.UserService;
import com.example.barkamol_avlod.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.barkamol_avlod.status.AppStatusMessage.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    @Override
    public ResponseDto<UserDto> update(UserDto userDto) {
        if (userDto.getId() == null){
            return ResponseDto.<UserDto>builder()
                    .message(NULL_VALUE)
                    .data(userDto)
                    .build();
        }
        try {
            Optional<Users> userOptional = userRepository.findById(userDto.getId());

            if (userOptional.isEmpty()){
                return ResponseDto.<UserDto>builder()
                        .message(NOT_FOUND)
                        .data(userDto)
                        .build();
            }
            Users users = userOptional.get();

            users.setPassword(userDto.getPassword() != null ? userDto.getPassword() : users.getPassword());

            if (userDto.getUsername() != null) {
                Optional<Users> user1 = userRepository.findFirstByUsername(userDto.getUsername());
                if (user1.isEmpty()){
                    users.setUsername(userDto.getUsername());
                }
                else {
                    return ResponseDto.<UserDto>builder()
                            .message("This username already exists!")
                            .build();
                }
            }

            userRepository.save(users);

            return ResponseDto.<UserDto>builder()
                    .data(userMapper.toDto(users))
                    .success(true)
                    .message(OK)
                    .build();
        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .message(DATABASE_ERROR + e.getMessage())
                    .build();
        }
    }
    @Override
    public ResponseDto<String> loginUser(LoginDto loginDto) {
        Users users = loadUserByUsername(loginDto.getUsername());
        if (!encoder.matches(loginDto.getPassword(), users.getPassword())){
            return ResponseDto.<String>builder()
                    .message("Password is not correct "+ users.getPassword())
                    .build();
        }

        return ResponseDto.<String>builder()
                .success(true)
                .message(OK)
                .data(jwtService.generateToken(users))
                .build();
    }

    private Users loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = userRepository.findFirstByUsername(username);
        if (users.isEmpty()) throw new UsernameNotFoundException("User with username : " + username + " is not found");
        return users.get();
    }
}