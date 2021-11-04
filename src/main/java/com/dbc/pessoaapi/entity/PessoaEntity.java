package com.dbc.pessoaapi.entity;

import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PessoaEntity {
    private Integer idPessoa;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;

}