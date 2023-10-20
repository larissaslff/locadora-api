package com.locadora.cine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.cine.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
