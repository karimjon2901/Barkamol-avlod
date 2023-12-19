package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, String> {
}
