package br.com.estoque.iparts.persistence.repository.jpa;

import br.com.estoque.iparts.persistence.entity.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roles")
public interface RoleSpringDataRepository extends JpaRepository<RoleJpaEntity, Integer> {
    boolean existsByName(String name);
}
