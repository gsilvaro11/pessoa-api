package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.PessoaCreateDTO;
import com.dbc.pessoaapi.dto.PessoaDTO;
import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.service.EmailService;
import com.dbc.pessoaapi.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Validated
@RestController
@RequestMapping("/pessoa")
@Slf4j
@RequiredArgsConstructor
public class PessoaController {
    private final PessoaService pessoaService;

    @ApiOperation(value = "Cria uma pessoa")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Pessoa criada"),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    @PostMapping
    public PessoaDTO create(@Valid @RequestBody PessoaCreateDTO pessoaCreateDTO) throws Exception{
        log.info("Criando pessoa");
        PessoaDTO pessoa = pessoaService.create(pessoaCreateDTO);
        log.info("Criado com sucesso");
        return pessoa;
    }

    @ApiOperation(value = "Retorna uma lista de pessoas")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Pessoas da lista"),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    @GetMapping
    public List<PessoaDTO> list() {
        return pessoaService.list();
    }

    @ApiOperation(value = "Retornta lista de pessoas pelo nome")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Pessoas da lista"),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    @GetMapping("/byname")
    public List<PessoaDTO> listByName(@Valid @RequestParam("nome") String nome) {
        return pessoaService.listByName(nome);
    }

    @ApiOperation(value = "Retornta lista de pessoas pelo id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Pessoas da lista"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    @GetMapping("/byid")
    public PessoaDTO listById(@Valid @RequestParam("idPessoa") Integer idPessoa) throws RegraDeNegocioException {
        return pessoaService.listById(idPessoa);
    }

    @ApiOperation(value = "Atualiza lista de pessoas")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Atualizado com sucesso!"),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    @PutMapping("/{idPessoa}")
    public PessoaDTO update(@Valid @NotNull(message = "não pode ter id null") @PathVariable("idPessoa") Integer id,
                               @Valid @RequestBody PessoaCreateDTO pessoaCreateDTO) throws Exception {
        log.info("Atualizando pessoa");
        PessoaDTO pessoaDTO = pessoaService.update(id, pessoaCreateDTO);
        log.info("Atualizado com sucesso");
        return pessoaDTO;
    }

    @ApiOperation(value = "Deletar pessoa")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deletado com sucesso!"),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    @DeleteMapping("/{idPessoa}")
    public void delete(@PathVariable("idPessoa") Integer id) throws Exception {
        log.info("Deletando pessoa");
        pessoaService.delete(id);

        log.info("Deletado com sucesso");
    }
}
