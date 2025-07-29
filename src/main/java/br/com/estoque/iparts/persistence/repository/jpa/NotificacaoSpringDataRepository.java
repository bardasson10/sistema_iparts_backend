package br.com.estoque.iparts.persistence.repository.jpa;

import br.com.estoque.iparts.persistence.entity.NotificacaoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("notificacoes")
public interface NotificacaoSpringDataRepository extends JpaRepository<NotificacaoJpaEntity, Integer> {
    List<NotificacaoJpaEntity> findByFkUser_Id(Integer usuarioId);
}
