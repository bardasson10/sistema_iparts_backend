package br.com.estoque.iparts.infrastructure.controllers;

import br.com.estoque.iparts.application.ports.in.CreateUserUseCase;
import br.com.estoque.iparts.infrastructure.dto.mapper.UserDTOMapper;
import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;
import br.com.estoque.iparts.persistence.repository.jpa.UserSpringDataRepository;
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
    private final UserDTOMapper userDTOMapper;
    private  final UserSpringDataRepository userSpringDataRepository;


    public UserController(CreateUserUseCase createUserUseCase, UserDTOMapper userDTOMapper, UserSpringDataRepository userSpringDataRepository) {
        this.createUserUseCase = createUserUseCase;
        this.userDTOMapper = userDTOMapper;
        this.userSpringDataRepository = userSpringDataRepository;

    }


    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        var userCriadoDomain = createUserUseCase.executar(request);

        var userEntity = userSpringDataRepository.findById(userCriadoDomain.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado após criação"));

        var response = userDTOMapper.toResponse(userEntity);

        System.out.println("Usuário criado com sucesso: " + response);
        return ResponseEntity.ok(response);
    }


}
