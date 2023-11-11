package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.AboutOfSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutOfSchoolRepository extends JpaRepository<AboutOfSchool, Integer> {
}
