package br.com.estoque.iparts.application.ports.out;

import br.com.estoque.iparts.application.domain.model.Role;
import br.com.estoque.iparts.security.enums.RoleEnum;

import java.util.Optional;


public interface RoleRepositositoryPort {
    Role salvar(Role role);
    boolean existePorNome(String nome);
    Optional<Role> buscarPorNome(String nome );
}
