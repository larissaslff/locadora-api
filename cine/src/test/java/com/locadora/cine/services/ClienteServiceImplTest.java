package com.locadora.cine.services;

import static org.mockito.Mockito.when;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.locadora.cine.models.Cliente;
import com.locadora.cine.repositories.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void deveSalvarCliente() {

        Cliente clienteASalvar = Cliente.builder()
                .nome("Cliente nome")
                .sobrenome("Cliente sobrenome")
                .telefone("telefone")
                .endereco("endereco")
                .build();

        Cliente clienteSalvo = Cliente.builder()
                .id(1L)
                .nome("Cliente nome")
                .sobrenome("Cliente sobrenome")
                .telefone("telefone")
                .endereco("endereco")
                .build();

        when(clienteRepository.save(clienteASalvar)).thenReturn(clienteSalvo);

        ResponseEntity<Cliente> actual = clienteService.save(clienteASalvar);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(actual.getBody().getId()).isNotNull();
        assertThat(actual.getBody().getNome()).isEqualTo(clienteASalvar.getNome());
        
    }

    @Test
    void deveRetornarTodosClientes() {
        Cliente cliente01 = Cliente.builder()
                .nome("Cliente 01")
                .sobrenome("Cliente 01")
                .telefone("telefone")
                .endereco("endereco")
                .build();

        Cliente cliente02 = Cliente.builder()
                .nome("Cliente 02")
                .sobrenome("Cliente 02")
                .telefone("telefone")
                .endereco("endereco")
                .build();

        List<Cliente> clientes = List.of(cliente01, cliente02);

        when(clienteRepository.findAll()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> actual = clienteService.buscarClientes();

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody().size()).isEqualTo(clientes.size());
        assertThat(actual.getBody().get(0).getNome()).isEqualTo(cliente01.getNome());
        assertThat(actual.getBody().get(1).getSobrenome()).isEqualTo(cliente02.getSobrenome());

    }
}
