package br.com.estoque.iparts.infrastructure.controllers;

import br.com.estoque.iparts.application.ports.in.CreateUserUseCase;

import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/v0/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;


    public UserController(CreateUserUseCase createUserUseCase ) {
        this.createUserUseCase = createUserUseCase;
    }


    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        var response = createUserUseCase.executar(request);

        return ResponseEntity.ok(response);
    }


}
