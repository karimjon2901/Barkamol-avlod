package com.example.barkamol_avlod.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private String message;
    private boolean success;
    private T data;
}