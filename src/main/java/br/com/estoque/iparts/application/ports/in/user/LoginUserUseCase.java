package br.com.estoque.iparts.application.ports.in.user;

import br.com.estoque.iparts.infrastructure.dto.request.LoginRequest;
import br.com.estoque.iparts.infrastructure.dto.response.LoginResponse;

public interface LoginUserUseCase {

    LoginResponse executar(LoginRequest loginRequest);
}
