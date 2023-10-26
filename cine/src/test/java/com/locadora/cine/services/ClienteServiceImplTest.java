package com.locadora.cine.services;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
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

        private static Cliente cliente;

        @BeforeAll
        static void setUp() {
                cliente = Cliente.builder()
                                .id(1L)
                                .nome("Cliente 01")
                                .sobrenome("Cliente 01")
                                .telefone("telefone")
                                .endereco("endereco")
                                .build();
        }

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

                Cliente cliente02 = Cliente.builder()
                                .nome("Cliente 02")
                                .sobrenome("Cliente 02")
                                .telefone("telefone")
                                .endereco("endereco")
                                .build();

                List<Cliente> clientes = List.of(cliente, cliente02);

                when(clienteRepository.findAll()).thenReturn(clientes);

                ResponseEntity<List<Cliente>> actual = clienteService.buscarClientes();

                assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
                assertThat(actual.getBody().size()).isEqualTo(clientes.size());
                assertThat(actual.getBody().get(0).getNome()).isEqualTo(cliente.getNome());
                assertThat(actual.getBody().get(1).getSobrenome()).isEqualTo(cliente02.getSobrenome());

        }

    @Test
    void deveRetornarClienteQuandoExistentePorId(){
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> clienteEncontrado = clienteService.buscarClientePorId(1L);

        assertThat(clienteEncontrado.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(clienteEncontrado.getBody().getId()).isEqualTo(cliente.getId());
        assertThat(clienteEncontrado.getBody().getNome()).isEqualTo(cliente.getNome());
    }

    @Test
    void nãoDeveRetornarClienteQuandoInexisteId(){
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Cliente> clienteEncontrado = clienteService.buscarClientePorId(100L);

        assertThat(clienteEncontrado.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

        @Test
        void deveAtualizarClienteExistente() {
                Cliente clienteAtualizado = cliente = Cliente.builder()
                                .id(1L)
                                .nome("Cliente Atualizado 01")
                                .sobrenome("Cliente Atualizado 01")
                                .telefone("telefone")
                                .endereco("endereco")
                                .build();
        
        ResponseEntity<Cliente> clienteResposta = clienteService.atualizarCliente(1L, clienteAtualizado);

        assertThat(clienteResposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
}
