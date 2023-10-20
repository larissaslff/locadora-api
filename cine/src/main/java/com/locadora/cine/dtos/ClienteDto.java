package com.locadora.cine.dtos;

import java.util.Set;

import lombok.Builder;

@Builder
public record ClienteDto(String nome, String sobrenome, String endereco, String telefone, Set<AluguelDTO> alugueis) {
    
}
