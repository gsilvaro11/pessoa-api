package com.dbc.pessoaapi.controller;


import com.dbc.pessoaapi.dto.EnderecoCreateDTO;
import com.dbc.pessoaapi.dto.EnderecoDTO;

import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/endereco")
@Validated
@Slf4j
@RequiredArgsConstructor
public class EnderecoContoller {
    private final EnderecoService enderecoService;

    @GetMapping
    @ApiOperation(value = "Lista de endereços")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    public List<EnderecoDTO> list(){return enderecoService.list();}

    @GetMapping("/{idEndereco}/endereco")
    @ApiOperation(value = "Lista de endereços por id de endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    public EnderecoDTO listByEndereco(@PathVariable("idEndereco") Integer idEndereco) throws Exception {
        return enderecoService.listByEndereco(idEndereco);
    }

    @ApiOperation(value = "Lista endereço por pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    @GetMapping("/{idPessoa}/pessoa")
    public List<EnderecoDTO> listByPessoa(@PathVariable("idPessoa") Integer idPessoa) throws Exception {
        return enderecoService.listByPessoa(idPessoa);
    }


    @PutMapping("/{idEndereco}")
    @ApiOperation(value = "Atualiza endereços")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço atualizado"),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    public EnderecoDTO update(@Valid @PathVariable("idEndereco") Integer id,
                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO)
            throws Exception {
        log.info("Atualizando pessoa");
        EnderecoDTO enderecoDTO = enderecoService.update(id, enderecoCreateDTO);
        log.info("Atualizado com sucesso");
        return enderecoDTO;
    }

    @PostMapping("/{idPessoa}")
    @ApiOperation(value = "Criar endereços")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço criado"),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    public EnderecoDTO create(@Valid @PathVariable("idPessoa") Integer id,
                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO)
                                throws RegraDeNegocioException {
        log.info("Criando pessoa");
        EnderecoDTO enderecoDTO = enderecoService.create(id, enderecoCreateDTO);
        log.info("Criado com sucesso");
        return enderecoDTO;
    }

    @DeleteMapping("/{idEndereco}")
    @ApiOperation(value = "Deletar endereços")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço deletado"),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada um execeção")
    })
    public void delete(@PathVariable("idEndereco") Integer id) throws Exception {
        log.info("Deletando pessoa");
        enderecoService.delete(id);
        log.info("Deletado com sucesso");
    }
}