package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> getAllByOrderByCreatedAtDesc();
}