package com.dbc.pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PessoaCreateDTO {
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value = "Nome da pessoa")
    private String nome;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value = "Email da pessoa")
    private String email;

    @NotNull(message = "Não pode ser nulo")
    @Past()
    @ApiModelProperty(value = "Data da pessoa")
    private LocalDate dataNascimento;

    @NotNull(message = "Não pode ser nulo")
    @NotEmpty(message = "Não pode estar em branco")
    @Size(max = 11, min = 11)
    @ApiModelProperty(value = "Cpf da pessoa")
    private String cpf;
}
