package com.example.barkamol_avlod.entity;

import jakarta.persistence.*;
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
public class Category {
    @Id
    private String id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Category parentId;

    private String nameRU;
    private String nameUZ;
    private String nameEN;

    private String descriptionRU;
    private String descriptionUZ;
    private String descriptionEN;
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;
}