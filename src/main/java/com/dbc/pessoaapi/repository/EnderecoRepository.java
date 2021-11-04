package com.dbc.pessoaapi.repository;


import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {
    private static List<EnderecoEntity> listaEnderecoEntity = new ArrayList<>();
    private final AtomicInteger COUNTERPESSOA = new AtomicInteger();
    private final AtomicInteger COUNTERENDERECO = new AtomicInteger();

    public EnderecoRepository() {
        listaEnderecoEntity.add(new EnderecoEntity(COUNTERENDERECO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(),
                "1", "longradouro", 105,
                "null", "91420612", "Porto Alegre", "RS", "Brasil"));

        listaEnderecoEntity.add(new EnderecoEntity(COUNTERENDERECO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(),
                "1", "longradouro", 105,
                "null", "91420612", "Porto Alegre", "RS", "Brasil"));

        listaEnderecoEntity.add(new EnderecoEntity(COUNTERENDERECO.incrementAndGet(), COUNTERPESSOA.incrementAndGet(),
                "1", "longradouro", 105,
                "null", "91420612", "Porto Alegre", "RS", "Brasil"));
    }

    public List<EnderecoEntity> list() {
        return listaEnderecoEntity;
    }

    public EnderecoEntity listByEndereco(Integer id) throws Exception{
        return listaEnderecoEntity.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(id))
                .findFirst().orElseThrow(() -> new Exception("Erro"));

    }

    public List<EnderecoEntity> listByPessoa(Integer idPessoa){
        return listaEnderecoEntity.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdPessoa().equals(idPessoa))
                .collect(Collectors.toList());
    }

    public EnderecoEntity create(EnderecoEntity enderecoEntity) {
        enderecoEntity.setIdEndereco(COUNTERENDERECO.incrementAndGet());
        listaEnderecoEntity.add(enderecoEntity);
        return enderecoEntity;
    }

    public EnderecoEntity update(Integer id, EnderecoEntity enderecoEntityAtualizado) throws Exception {
        EnderecoEntity enderecoEntityRecuperado = listaEnderecoEntity.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        enderecoEntityRecuperado.setIdEndereco(id);
        enderecoEntityRecuperado.setIdPessoa(enderecoEntityAtualizado.getIdPessoa());
        enderecoEntityRecuperado.setTipo(enderecoEntityAtualizado.getTipo());
        enderecoEntityRecuperado.setLogradouro(enderecoEntityAtualizado.getLogradouro());
        enderecoEntityRecuperado.setNumero(enderecoEntityAtualizado.getNumero());
        enderecoEntityRecuperado.setComplemento(enderecoEntityAtualizado.getComplemento());
        enderecoEntityRecuperado.setCep(enderecoEntityAtualizado.getCep());
        enderecoEntityRecuperado.setCidade(enderecoEntityAtualizado.getCidade());
        enderecoEntityRecuperado.setEstado(enderecoEntityAtualizado.getEstado());
        enderecoEntityRecuperado.setPais(enderecoEntityAtualizado.getPais());
        return enderecoEntityRecuperado;
    }

    public void delete(Integer id) throws Exception {
        EnderecoEntity enderecoEntityRecuperado = listaEnderecoEntity.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        listaEnderecoEntity.remove(enderecoEntityRecuperado);
    }

}
