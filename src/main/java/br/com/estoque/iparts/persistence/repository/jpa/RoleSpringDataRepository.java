package br.com.estoque.iparts.persistence.repository.jpa;

import br.com.estoque.iparts.persistence.entity.RoleJpaEntity;
import br.com.estoque.iparts.security.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("roles")
public interface RoleSpringDataRepository extends JpaRepository<RoleJpaEntity, Integer> {
    boolean existsByName(RoleEnum name);
    Optional<RoleJpaEntity> findByName(RoleEnum name);
}
