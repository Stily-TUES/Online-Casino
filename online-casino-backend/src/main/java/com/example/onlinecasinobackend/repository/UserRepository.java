package com.example.onlinecasinobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.onlinecasinobackend.model.User; 

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}