package br.com.estoque.iparts.application.ports.out;

import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.persistence.entity.UserJpaEntity;

public interface UserRepositoryPort {
    User salvar(User user);
    boolean existePorEmail(String email);


}
