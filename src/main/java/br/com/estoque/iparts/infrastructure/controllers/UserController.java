package br.com.estoque.iparts.infrastructure.controllers;

import br.com.estoque.iparts.application.ports.in.user.CreateUserUseCase;

import br.com.estoque.iparts.application.ports.in.user.FindUserUseCase;
import br.com.estoque.iparts.application.ports.in.user.UpdateUserUseCase;
import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.request.UpdateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/v0/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private  final UpdateUserUseCase updateUserUseCase;
    private final FindUserUseCase findUserUseCase;


    public UserController(
            CreateUserUseCase createUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            FindUserUseCase findUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.findUserUseCase = findUserUseCase;
    }


    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        var response = createUserUseCase.executar(request);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponse> update(
            @PathVariable Integer id,
            @RequestParam(required = false) Boolean isAtivo,
            @Valid @RequestBody UpdateUserRequest request) {
        var response = updateUserUseCase.executar(id, isAtivo, request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/search/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponse> buscarPorId(@PathVariable Integer id) {
        var response = findUserUseCase.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/all")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Iterable<UserResponse>> buscarTodos() {
        var response = findUserUseCase.buscarTodos();
        return ResponseEntity.ok(response);
    }


}
