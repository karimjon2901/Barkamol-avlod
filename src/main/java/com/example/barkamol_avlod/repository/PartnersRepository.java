package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnersRepository extends JpaRepository<Partners, Integer> {
}
