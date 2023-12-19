package com.example.barkamol_avlod.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partners {
    @Id
    private String id;
    private String image;
    private String nameRU;
    private String nameUZ;
    private String nameEN;
    @CreatedDate
    private LocalDateTime createdAt;
}
