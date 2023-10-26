package com.locadora.cine.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.locadora.cine.models.Cliente;

public interface ClienteService {
 
    ResponseEntity<Cliente> save(Cliente cliente);

    ResponseEntity<List<Cliente>> buscarClientes();

    ResponseEntity<Cliente> buscarClientePorId(Long id);
}
