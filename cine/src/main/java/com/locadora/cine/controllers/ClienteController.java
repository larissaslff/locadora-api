package com.locadora.cine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.cine.models.Cliente;
import com.locadora.cine.services.ClienteService;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
        ResponseEntity<Cliente> clienteSalvo = clienteService.save(cliente);
        return clienteSalvo;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientes() {
        return clienteService.buscarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return clienteService.atualizarCliente(id, clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        return clienteService.deletarCliente(id);
    }
}
