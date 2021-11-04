package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.client.DadosPessoaisClient;
import com.dbc.pessoaapi.dto.DadosPessoaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DadosPessoaisService {
    private final DadosPessoaisClient dadosPessoaisClient;
    private final ObjectMapper objectMapper;

    public List<DadosPessoaisDTO> list(){
        return dadosPessoaisClient.list();
    }

    public DadosPessoaisDTO create(DadosPessoaisDTO dadosPessoaisDTO){
        return dadosPessoaisClient.post(dadosPessoaisDTO);
    }
}
