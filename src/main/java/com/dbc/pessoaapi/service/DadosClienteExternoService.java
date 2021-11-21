package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.client.DadosClienteExterno;
import com.dbc.pessoaapi.dto.DadosClienteExternoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DadosClienteExternoService {
    private final DadosClienteExterno dadosExternos;
    private final ObjectMapper objectMapper;

    public List<DadosClienteExternoDTO> list(){
        return dadosExternos.list();
    }

//    public DadosClienteExternoDTO create(DadosClienteExternoDTO dadosClienteExternoDTO){
//        return dadosPessoaisClient.post(dadosClienteExternoDTO);
//    }
}
