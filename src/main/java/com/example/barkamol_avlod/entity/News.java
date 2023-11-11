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
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;

    private String titleRU;
    private String titleUZ;
    private String titleEN;

    private String subTitleRU;
    private String subTitleUZ;
    private String subTitleEN;

    private String descriptionRU;
    private String descriptionUZ;
    private String descriptionEN;
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;
}
