package br.com.estoque.iparts.application.ports.in.user;

import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;

public interface CreateUserUseCase {
    UserResponse executar(CreateUserRequest request);
}
