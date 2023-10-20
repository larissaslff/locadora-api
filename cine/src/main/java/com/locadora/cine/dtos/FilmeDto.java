package com.locadora.cine.dtos;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record FilmeDto(String titulo, LocalDateTime anoLancamento, String genero) {

}
