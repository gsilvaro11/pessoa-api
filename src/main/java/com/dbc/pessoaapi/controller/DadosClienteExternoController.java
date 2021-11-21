package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.DadosClienteExternoDTO;
import com.dbc.pessoaapi.service.DadosClienteExternoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/dados-cliente")
@RequiredArgsConstructor
public class DadosClienteExternoController {
    private final DadosClienteExternoService dadosClienteExternoService;

    @GetMapping
    public List<DadosClienteExternoDTO> list(){
        return dadosClienteExternoService.list();
    }

//    @PostMapping
//    public DadosClienteExternoDTO create(@Valid @RequestBody DadosClienteExternoDTO dadosClienteExternoDTO){
//        return dadosClienteExternoService.create(dadosClienteExternoDTO);
//    }

}
