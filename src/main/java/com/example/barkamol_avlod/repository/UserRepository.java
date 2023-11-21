package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findFirstByUsername(String username);
}
