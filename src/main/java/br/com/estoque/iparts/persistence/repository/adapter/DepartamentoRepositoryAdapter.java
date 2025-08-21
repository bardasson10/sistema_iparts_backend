package br.com.estoque.iparts.persistence.repository.adapter;

import br.com.estoque.iparts.application.domain.model.Departamento;
import br.com.estoque.iparts.application.ports.out.DepartamentoRepositoryPort;
import br.com.estoque.iparts.persistence.mapper.DepartamentoPersistenceMapper;
import br.com.estoque.iparts.persistence.repository.jpa.DepartamentoSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DepartamentoRepositoryAdapter implements DepartamentoRepositoryPort {


    private final DepartamentoSpringDataRepository jpaRepository;

    private final  DepartamentoPersistenceMapper mapper;

    public  DepartamentoRepositoryAdapter(DepartamentoSpringDataRepository jpaRepository, DepartamentoPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Departamento salvar(Departamento departamento) {
        var departamentoEntity = mapper.toEntity(departamento);
        var savedDepartamento = jpaRepository.save(departamentoEntity);
        return mapper.toDomain(savedDepartamento);
    }

    @Override
    public Optional<Departamento> buscarPorId(Integer id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Departamento> buscarPorNome(String nome) {
        return jpaRepository.findByNome(nome)
                .map(mapper::toDomain);
    }


    @Override
    public boolean existePorNome(String nome) {
        return jpaRepository.existsByNome(nome);
    }
}