package com.dbc.pessoaapi.service;


import com.dbc.pessoaapi.dto.EnderecoCreateDTO;
import com.dbc.pessoaapi.dto.EnderecoDTO;
import com.dbc.pessoaapi.dto.PessoaDTO;
import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.EnderecoRepository;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    public EnderecoDTO create(Integer id, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        EnderecoEntity entity = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        PessoaEntity pessoaEntity = pessoaRepository.list().stream()
                        .filter(x -> x.getIdPessoa().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
        entity.setIdPessoa(id);
        EnderecoEntity atualizado = enderecoRepository.create(entity);
        EnderecoDTO dto = objectMapper.convertValue(atualizado, EnderecoDTO.class);
        return dto;
    }

    public List<EnderecoDTO> list(){
        return enderecoRepository.list().stream()
                .map(x -> objectMapper.convertValue(x, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO listByEndereco(Integer id) throws Exception{
        EnderecoEntity entity = enderecoRepository.listByEndereco(id);
        EnderecoDTO dto = objectMapper.convertValue(entity, EnderecoDTO.class);
        return dto;
    }

    public List<EnderecoDTO> listByPessoa(Integer idPessoa) throws Exception{
        return enderecoRepository.listByPessoa(idPessoa).stream()
                .map(x -> objectMapper.convertValue(x, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws Exception{
        EnderecoEntity entity = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        EnderecoEntity enderecoId = enderecoRepository.list().stream()
                .filter(x -> x.getIdEndereco().equals(idEndereco))
                .findFirst().orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));

        EnderecoEntity update = enderecoRepository.update(idEndereco, entity);
        EnderecoDTO dto = objectMapper.convertValue(update, EnderecoDTO.class);
        return dto;
    }

    public void delete(Integer id) throws Exception {
        enderecoRepository.delete(id);
    }

}
