package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.PhotoGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoGalleryRepository extends JpaRepository<PhotoGallery, String> {
}
