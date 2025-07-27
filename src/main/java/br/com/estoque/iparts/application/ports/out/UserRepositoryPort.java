package br.com.estoque.iparts.application.ports.out;

import br.com.estoque.iparts.application.domain.model.User;

public interface UserRepositoryPort {
    User salvar(User user);
    boolean existePorEmail(String email);
}
