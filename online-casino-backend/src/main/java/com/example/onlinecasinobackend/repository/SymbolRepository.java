package com.example.onlinecasinobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinecasinobackend.model.SymbolEntity;

public interface SymbolRepository extends JpaRepository<SymbolEntity, Long> {

}