package br.com.estoque.iparts.infrastructure.controllers;

import br.com.estoque.iparts.application.ports.in.user.LoginUserUseCase;
import br.com.estoque.iparts.infrastructure.dto.request.LoginRequest;
import br.com.estoque.iparts.infrastructure.dto.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AuthController {

    private final LoginUserUseCase loginUserUseCase;

    public AuthController(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = loginUserUseCase.executar(loginRequest);
        return ResponseEntity.ok(response);
    }

}
