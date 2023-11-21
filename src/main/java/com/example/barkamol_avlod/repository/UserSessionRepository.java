package com.example.barkamol_avlod.repository;

import com.example.barkamol_avlod.entity.UserSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends CrudRepository<UserSession,String> {
}
