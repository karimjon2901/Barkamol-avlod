package com.example.barkamol_avlod.service.maper;

public interface CommonMapper <D, E>{
    D toDto(E e);
    E toEntity(D d);
}
