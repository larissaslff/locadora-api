package com.locadora.cine.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @Override
    public ResponseEntity<Cliente> buscarClientePorId(Long id) {
        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);

        if (clienteBuscado.isPresent()) {
            return ResponseEntity.ok(clienteBuscado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Cliente> atualizarCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);

        if (clienteBuscado.isPresent()) {
            clienteAtualizado.setId(id);
            Cliente clienteSalvoAtualizado = clienteRepository.save(clienteAtualizado);
            return ResponseEntity.ok(clienteSalvoAtualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deletarCliente(Long id) {
        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);

        if (clienteBuscado.isPresent()) {
            clienteRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }

}
