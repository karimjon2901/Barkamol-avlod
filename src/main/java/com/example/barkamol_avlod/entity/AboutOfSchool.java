package com.example.barkamol_avlod.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AboutOfSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titleRU;
    private String titleUZ;
    private String titleEN;

    private String descriptionRU;
    private String descriptionUZ;
    private String descriptionEN;

    @UpdateTimestamp
    private LocalDateTime createdAt;
}
