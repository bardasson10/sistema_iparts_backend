package br.com.estoque.iparts.application.ports.in;

import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;

public interface CreateUserUseCase {
    User executar(CreateUserRequest request);
}
