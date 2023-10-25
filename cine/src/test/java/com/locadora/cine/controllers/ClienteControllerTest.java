package com.locadora.cine.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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


@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @MockBean
    private ClienteServiceImpl clienteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveSalvarCliente() throws JsonProcessingException, Exception {

        Cliente cliente = Cliente.builder()
        .nome("Nome")
        .sobrenome("Sobrenome")
        .endereco("Endereço")
        .build();

        when(clienteService.save(cliente)).thenReturn(new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED));

        mockMvc.perform(post("/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(cliente.getNome()))
                .andExpect(jsonPath("$.sobrenome").value(cliente.getSobrenome()))
                .andReturn();
    }
}
