package br.com.estoque.iparts.persistence.repository.jpa;

import br.com.estoque.iparts.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("users")
public interface UserSpringDataRepository extends JpaRepository<UserJpaEntity, Integer> {

    Boolean  existsByEmail(String email);

    Optional<UserJpaEntity> findById(Integer Id);

    Optional<UserJpaEntity> findByEmail(String email);

    @Query("SELECT u FROM UserJpaEntity u JOIN FETCH u.fkDepartamento WHERE u.id = :id")
    Optional<UserJpaEntity> findByIdWithDepartamento(Integer id);

    @Query("SELECT u FROM UserJpaEntity u JOIN FETCH u.fkDepartamento order by u.id ASC")
    List<UserJpaEntity> findAllWithDepartamento();



}

