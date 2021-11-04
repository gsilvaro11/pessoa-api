package com.dbc.pessoaapi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoCreateDTO {
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "Tipo de contato")
    private String tipoContato;

    @NotNull
    @Size(max = 13)
    @ApiModelProperty(value = "Numero contato")
    private String numero;

    @Size(max = 110)
    @ApiModelProperty(value = "Descrição contato")
    private String descricao;

}