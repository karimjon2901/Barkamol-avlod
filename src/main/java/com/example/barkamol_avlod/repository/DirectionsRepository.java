package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.Directions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectionsRepository extends JpaRepository<Directions, String> {
    List<Directions> findAllByCategoryId(String id);
}
