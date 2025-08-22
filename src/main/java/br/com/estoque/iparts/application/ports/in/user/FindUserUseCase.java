package br.com.estoque.iparts.application.ports.in.user;

import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface FindUserUseCase  {

    Optional<UserResponse> buscarPorId(Integer id);


    List<UserResponse> buscarTodos();
}
