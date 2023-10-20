package com.locadora.cine.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Builder;

@Builder
public record AluguelDTO(LocalDateTime dataAlugel, LocalDateTime dataDevolucao, ClienteDto cliente, Set<FilmeDto> filmes) {

}
