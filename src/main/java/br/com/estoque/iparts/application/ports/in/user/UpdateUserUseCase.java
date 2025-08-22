package br.com.estoque.iparts.application.ports.in.user;

import br.com.estoque.iparts.infrastructure.dto.request.UpdateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;


public interface UpdateUserUseCase {

    UserResponse executar(Integer Id, Boolean IsAtivo , UpdateUserRequest userParaAtualizar);
}
