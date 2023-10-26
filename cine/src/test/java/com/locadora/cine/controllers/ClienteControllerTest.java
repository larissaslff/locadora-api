package com.locadora.cine.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locadora.cine.models.Cliente;
import com.locadora.cine.services.ClienteServiceImpl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    /**
     *
     */
    private static final String V1_CLIENTES = "/v1/clientes";

    @MockBean
    private ClienteServiceImpl clienteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Cliente cliente;

    @BeforeAll
    static void setUp() {
        cliente = Cliente.builder()
                .id(1L)
                .nome("Nome")
                .sobrenome("Sobrenome")
                .endereco("Endereço")
                .build();
    }

    @Test
    void deveSalvarCliente() throws JsonProcessingException, Exception {

        when(clienteService.save(cliente)).thenReturn(new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED));

        mockMvc.perform(post(V1_CLIENTES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(cliente.getNome()))
                .andExpect(jsonPath("$.sobrenome").value(cliente.getSobrenome()))
                .andExpect(jsonPath("$.endereco").value(cliente.getEndereco()))
                .andExpect(jsonPath("$.telefone").value(cliente.getTelefone()))
                .andExpect(jsonPath("$.alugueis").value(cliente.getAlugueis()))
                .andReturn();
    }

    @Test
    void deveBuscarTodosOsClientes() throws Exception {

        Cliente cliente02 = cliente = Cliente.builder()
                .nome("Nome2")
                .sobrenome("Sobrenome2")
                .endereco("Endereço2")
                .telefone("telefone")
                .build();

        ResponseEntity<List<Cliente>> serviceResponse = ResponseEntity.ok(List.of(cliente, cliente02));

        when(clienteService.buscarClientes()).thenReturn(serviceResponse);

        mockMvc.perform(get(V1_CLIENTES)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome").value(cliente.getNome()))
                .andExpect(jsonPath("$[0].telefone").value(cliente.getTelefone()))
                .andExpect(jsonPath("$[1].nome").value(cliente02.getNome()))
                .andExpect(jsonPath("$[1].telefone").value(cliente02.getTelefone()));
    }

    @Test
    void deveEncontrarUmClientePorId() throws Exception {

        when(clienteService.buscarClientePorId(anyLong())).thenReturn(ResponseEntity.ok(cliente));

        mockMvc.perform(get(V1_CLIENTES+"/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(cliente.getId()))
        .andExpect(jsonPath("$.nome").value(cliente.getNome()));
    }

    @Test
    void naoDeveEncontrarUmClientePorNaoExistir() throws Exception {

        when(clienteService.buscarClientePorId(anyLong())).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(get(V1_CLIENTES+"/100"))
        .andExpect(status().isNotFound());
    }
}
