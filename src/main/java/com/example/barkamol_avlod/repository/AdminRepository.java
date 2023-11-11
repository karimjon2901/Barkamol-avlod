package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findFirstByUsername(String username);
}
