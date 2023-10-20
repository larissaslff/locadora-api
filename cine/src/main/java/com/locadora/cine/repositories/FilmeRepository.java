package com.locadora.cine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.cine.models.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    
}
