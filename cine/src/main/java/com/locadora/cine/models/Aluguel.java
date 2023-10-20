package com.locadora.cine.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.locadora.cine.dtos.AluguelDTO;
import com.locadora.cine.dtos.ClienteDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataAlugel;

    private LocalDateTime dataDevolucao;

    @ManyToOne
    private Cliente cliente;

    @OneToMany
    private Set<Filme> filmes;

    public AluguelDTO toDTO() {
        return AluguelDTO.builder()
                .dataAlugel(dataAlugel)
                .dataDevolucao(dataDevolucao)
                .cliente(ClienteDto.builder().build())
                .build();
    }
}
