package br.com.estoque.iparts.application.ports.out;

import br.com.estoque.iparts.application.domain.model.Departamento;

import java.util.Optional;

public interface DepartamentoRepositoryPort {
    Departamento salvar(Departamento departamento);
    Optional<Departamento> buscarPorId(Integer id);
    boolean existePorNome(String nome);
}
