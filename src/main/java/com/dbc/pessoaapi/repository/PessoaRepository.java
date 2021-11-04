package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.dto.PessoaDTO;
import com.dbc.pessoaapi.entity.PessoaEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class PessoaRepository {
    private static List<PessoaEntity> listaPessoaEntity = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public PessoaRepository() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020
        listaPessoaEntity.add(new PessoaEntity(COUNTER.incrementAndGet() /*1*/, "Maicon Gerardi", "nei.guilherme@dbccompany.com.br", LocalDate.parse("10/10/1990", formatter), "12345678910"));
        listaPessoaEntity.add(new PessoaEntity(COUNTER.incrementAndGet() /*2*/, "Charles Pereira", "nei.guilherme@dbccompany.com.br", LocalDate.parse("08/05/1985", formatter), "12345678911"));
        listaPessoaEntity.add(new PessoaEntity(COUNTER.incrementAndGet() /*3*/, "Marina Oliveira","nei.guilherme@dbccompany.com.br", LocalDate.parse("30/03/1970", formatter), "12345678912"));
    }

    public PessoaEntity create(PessoaEntity pessoaEntity) throws Exception{
        pessoaEntity.setIdPessoa(COUNTER.incrementAndGet());
        listaPessoaEntity.add(pessoaEntity);
        return pessoaEntity;
    }

    public List<PessoaEntity> list() {
        return listaPessoaEntity;
    }

    public PessoaEntity update(Integer id,
                            PessoaEntity pessoaEntityAtualizado) throws Exception {
        PessoaEntity pessoaRecuperada = listaPessoaEntity.stream()
                .filter(pessoaDTO -> pessoaDTO.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));
        pessoaRecuperada.setCpf(pessoaEntityAtualizado.getCpf());
        pessoaRecuperada.setNome(pessoaEntityAtualizado.getNome());
        pessoaRecuperada.setEmail(pessoaEntityAtualizado.getEmail());
        pessoaRecuperada.setDataNascimento(pessoaEntityAtualizado.getDataNascimento());
        return pessoaRecuperada;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        PessoaEntity pessoaRecuperada = listaPessoaEntity.stream()
                .filter(pessoaEntity -> pessoaEntity.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));
        listaPessoaEntity.remove(pessoaRecuperada);

    }

    public List<PessoaEntity> listByName(String nome) {
        return listaPessoaEntity.stream()
                .filter(pessoaEntity -> pessoaEntity.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }

    public PessoaEntity listById(Integer idPessoa) throws RegraDeNegocioException{
        return listaPessoaEntity.stream()
                .filter(pessoaEntity -> pessoaEntity.getIdPessoa().equals(idPessoa))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));
    }
}
