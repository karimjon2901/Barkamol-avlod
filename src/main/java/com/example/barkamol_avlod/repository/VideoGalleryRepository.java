package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.VideoGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoGalleryRepository extends JpaRepository<VideoGallery, Integer> {
}
