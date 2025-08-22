package br.com.estoque.iparts.application.ports.out;

import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.persistence.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User salvar(User user);
    boolean existePorEmail(String email);
    Optional<User> buscarPorEmail(String email);
    Optional<User> buscarPorId(Integer id);
    List<User> buscarTodos();
}
