package br.com.estoque.iparts.application.ports.out;

import br.com.estoque.iparts.application.domain.model.Role;

public interface RoleRepositositoryPort {
    Role salvar(Role role);
}
