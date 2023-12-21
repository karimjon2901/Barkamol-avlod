package com.example.barkamol_avlod.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private String id;
    private String image;
    private String nameRU;
    private String nameUZ;
    private String nameEN;
    private String positionRU;
    private String positionUZ;
    private String positionEN;
    private String phoneNumber;
    private String email;
    private String telegram;
    private String instagram;
    private String facebook;
    private String role;
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;
}
