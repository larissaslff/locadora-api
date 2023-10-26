package com.locadora.cine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.locadora.cine.models.Cliente;
import com.locadora.cine.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ResponseEntity<Cliente> save(Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Cliente>> buscarClientes() {
        List<Cliente> todosClientes = clienteRepository.findAll();
        return ResponseEntity.ok(todosClientes);
    }

    public Object buscarPorId(long anyLong) {
        return null;
    }

    
    
}
