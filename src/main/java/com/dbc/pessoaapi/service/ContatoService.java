package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.ContatoDTO;
import com.dbc.pessoaapi.entity.ContatoCreateDTO;
import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.ContatoRepository;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    public ContatoDTO create(Integer id, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        ContatoEntity contaCriadaEntity = objectMapper.convertValue(contatoCreateDTO, ContatoEntity.class);
        PessoaEntity pessoaEntity = pessoaRepository.list().stream()
                .filter(x -> x.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
        contaCriadaEntity.setIdPessoa(id);
        ContatoEntity atualizado = contatoRepository.create(contaCriadaEntity);
        ContatoDTO dto = objectMapper.convertValue(atualizado, ContatoDTO.class);
        return dto;
    }

    public List<ContatoDTO> list(){
        return contatoRepository.list().stream()
                .map(x -> objectMapper.convertValue(x, ContatoDTO.class))
                .collect(Collectors.toList());
    }

    public ContatoDTO update(Integer id, ContatoCreateDTO contatoCreateDTO) throws Exception {
        ContatoEntity entity = objectMapper.convertValue(contatoCreateDTO, ContatoEntity.class);
        ContatoEntity atualizado = contatoRepository.update(id, entity);
        ContatoDTO dto = objectMapper.convertValue(atualizado, ContatoDTO.class);
        return dto;
    }

    public void delete(Integer id) throws Exception {
        contatoRepository.delete(id);
    }

    public List<ContatoDTO> listByNumero(String numero) {
        return contatoRepository.listByNumero(numero).stream()
                .map(x -> objectMapper.convertValue(x, ContatoDTO.class))
                .collect(Collectors.toList());
    }

}
