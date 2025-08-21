package br.com.estoque.iparts.application.ports.in;

import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;

public interface CreateUserUseCase {
    UserResponse executar(CreateUserRequest request);
}
