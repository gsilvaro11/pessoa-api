package com.dbc.pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnderecoDTO {
    private Integer idEndereco;
    private Integer idPessoa;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value = "Tipo de endereço")
    private String tipo;

    @Size(max = 250)
    @NotNull(message = "Não pode ser nulo")
    @ApiModelProperty(value = "Longradouro de endereço")
    private String logradouro;

    @NotNull(message = "Não pode ser nulo")
    @ApiModelProperty(value = "Numero de endereço")
    private Integer numero;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value = "Complemento de endereço")
    private String complemento;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    @Size(max = 8, min = 8)
    @ApiModelProperty(value = "Cep de endereço")
    private String cep;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    @Size(max = 250)
    @ApiModelProperty(value = "Cidade endereço")
    private String cidade;

    @NotNull(message = "Não pode ser nulo")
    @ApiModelProperty(value = "Estado endereço")
    private String estado;

    @NotNull(message = "Não pode ser nulo")
    @ApiModelProperty(value = "Pais endereço")
    private String pais;
}