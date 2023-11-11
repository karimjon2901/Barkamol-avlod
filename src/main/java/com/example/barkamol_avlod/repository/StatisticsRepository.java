package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {
}
