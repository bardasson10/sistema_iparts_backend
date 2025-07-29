package br.com.estoque.iparts.infrastructure.controllers;

import br.com.estoque.iparts.application.ports.in.CreateUserUseCase;
import br.com.estoque.iparts.infrastructure.dto.mapper.UserDTOMapper;
import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.response.UserReponse;
import br.com.estoque.iparts.persistence.repository.jpa.UserSpringDataRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/v0/users")
public class UserController {

    @Autowired
    CreateUserUseCase createUserUseCase;
    @Autowired
    UserDTOMapper userDTOMapper;
    @Autowired
    UserSpringDataRepository userJpaRepository;

    @PostMapping
    public ResponseEntity<UserReponse> create(@Valid @RequestBody CreateUserRequest request) {
        var userCriadoDomain = createUserUseCase.executar(request);

        var userEntity = userJpaRepository.findById(userCriadoDomain.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado após criação"));

        var response = userDTOMapper.toResponse(userEntity);
        return ResponseEntity.ok(response);
    }


}
