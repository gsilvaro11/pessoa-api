package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ContatoRepository {
    private static List<ContatoEntity> listaContatoEntities = new ArrayList<>();
    private final AtomicInteger COUNTERPESSOA = new AtomicInteger();
    private final AtomicInteger COUNTERCONTATO = new AtomicInteger();

    public ContatoRepository() {
        listaContatoEntities.add(new ContatoEntity(COUNTERCONTATO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(), "1", "101", "teste"));
        listaContatoEntities.add(new ContatoEntity(COUNTERCONTATO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(), "2", "102", "teste2"));
        listaContatoEntities.add(new ContatoEntity(COUNTERCONTATO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(), "1", "103", "teste3"));
    }


    public ContatoEntity create(ContatoEntity contatoEntityNovo) throws RegraDeNegocioException{
        contatoEntityNovo.setIdContato(COUNTERCONTATO.incrementAndGet());
        listaContatoEntities.add(contatoEntityNovo);
        return contatoEntityNovo;
    }

    public List<ContatoEntity> list() {
        return listaContatoEntities;
    }

    public ContatoEntity update(Integer id, ContatoEntity contatoEntityAtualizar) throws Exception {
        ContatoEntity contatoEntityRecuperado = listaContatoEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        contatoEntityRecuperado.setIdContato(contatoEntityAtualizar.getIdPessoa());
        contatoEntityRecuperado.setIdContato(id);
        contatoEntityRecuperado.setTipoContato(contatoEntityAtualizar.getTipoContato());
        contatoEntityRecuperado.setNumero(contatoEntityAtualizar.getNumero());
        contatoEntityRecuperado.setDescricao(contatoEntityAtualizar.getDescricao());
        log.info(contatoEntityRecuperado.getNumero());
        return contatoEntityRecuperado;
    }

    public void delete(Integer id) throws Exception {
        ContatoEntity contatoEntityRecuperada = listaContatoEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        listaContatoEntities.remove(contatoEntityRecuperada);
    }

    public List<ContatoEntity> listByNumero(String numero) {
        return listaContatoEntities.stream()
                .filter(contatoEntity -> contatoEntity.getNumero().toUpperCase().contains(numero.toUpperCase()))
                .collect(Collectors.toList());
    }
}
