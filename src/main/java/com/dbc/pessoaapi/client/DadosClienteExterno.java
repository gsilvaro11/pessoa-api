package com.dbc.pessoaapi.client;

import com.dbc.pessoaapi.dto.DadosClienteExternoDTO;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "cliente", url = "https://restaurante-api-vemser.herokuapp.com/swagger-ui/#/")
@Headers("Content-Type: application/json")
public interface DadosClienteExterno {

    @RequestLine("GET /cliente")
    List<DadosClienteExternoDTO> list();

//    @RequestLine("GET /dados-pessoais/{cpf}")
//    DadosClienteExterno getPorCpf(@Param("cpf") String cpf);
//
//    @RequestLine("POST /dados-pessoais")
//    DadosPessoaisDTO post(DadosPessoaisDTO dadosPessoaisDTO);
}
