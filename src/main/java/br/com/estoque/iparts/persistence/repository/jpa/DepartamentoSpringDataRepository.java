package br.com.estoque.iparts.persistence.repository.jpa;

import br.com.estoque.iparts.persistence.entity.DepartamentoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("departamento")
public interface DepartamentoSpringDataRepository extends JpaRepository<DepartamentoJpaEntity, Integer> {
    Boolean existsByNome(String nome);

    Optional<DepartamentoJpaEntity> findByNome(String nome);
    Optional<DepartamentoJpaEntity> findById(Integer Id);
}
