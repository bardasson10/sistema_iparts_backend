package br.com.estoque.iparts.application.service.user;

import br.com.estoque.iparts.application.ports.in.user.FindUserUseCase;
import br.com.estoque.iparts.infrastructure.dto.mapper.UserDTOMapper;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;
import br.com.estoque.iparts.persistence.repository.jpa.UserSpringDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindUserServiceImpl  implements FindUserUseCase {

    private final UserSpringDataRepository userJpaRepository;
    private final UserDTOMapper userDTOMapper;

    public FindUserServiceImpl(UserSpringDataRepository userJpaRepository, UserDTOMapper userDTOMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    @Transactional
    public Optional<UserResponse> buscarPorId(Integer id) {
        return userJpaRepository.findByIdWithDepartamento(id)
                .map(userDTOMapper::toResponse);
    }

    @Override
    @Transactional
    public List<UserResponse> buscarTodos() {
        return userJpaRepository.findAllWithDepartamento()
                .stream()
                .map(userDTOMapper::toResponse)
                .collect(Collectors.toList());
    }
}
