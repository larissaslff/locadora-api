package com.locadora.cine.models;

import java.util.Set;

import com.locadora.cine.dtos.AluguelDTO;
import com.locadora.cine.dtos.ClienteDto;
import com.locadora.cine.dtos.FilmeDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name = "tb_clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String endereco;

    private String telefone;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Aluguel> alugueis;

    public ClienteDto toDto() {
        return ClienteDto.builder()
        .nome(nome)
        .sobrenome(sobrenome)
        .endereco(endereco)
        .telefone(telefone)
        .alugueis(Set.of(AluguelDTO.builder().build()))
        .build();
    }
}
