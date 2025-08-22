package br.com.estoque.iparts.application.service.user;


import br.com.estoque.iparts.application.domain.model.Departamento;
import br.com.estoque.iparts.application.domain.model.Role;
import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.application.ports.in.user.CreateUserUseCase;
import br.com.estoque.iparts.application.ports.out.DepartamentoRepositoryPort;
import br.com.estoque.iparts.application.ports.out.PasswordEncoderPort;
import br.com.estoque.iparts.application.ports.out.RoleRepositositoryPort;
import br.com.estoque.iparts.application.ports.out.UserRepositoryPort;
import br.com.estoque.iparts.infrastructure.dto.mapper.UserDTOMapper;
import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;
import br.com.estoque.iparts.persistence.repository.jpa.UserSpringDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateUserServiceImpl  implements CreateUserUseCase {

    private final UserRepositoryPort userRepository;
    private final DepartamentoRepositoryPort departamentoRepository;
    private final RoleRepositositoryPort  roleRepository;
    private PasswordEncoderPort passwordEncoder;
    private final UserDTOMapper userDTOMapper;
    private  final UserSpringDataRepository userSpringDataRepository;


    public CreateUserServiceImpl(
            UserRepositoryPort userRepository,
            DepartamentoRepositoryPort departamentoRepository,
            RoleRepositositoryPort roleRepository,
            PasswordEncoderPort passwordEncoder,
            UserDTOMapper userDTOMapper,
            UserSpringDataRepository userSpringDataRepository)
    {
        this.userRepository = userRepository;
        this.departamentoRepository = departamentoRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDTOMapper = userDTOMapper;
        this.userSpringDataRepository = userSpringDataRepository;
    }


    @Override
    @Transactional
    public UserResponse executar(CreateUserRequest request) {
        if (userRepository.existePorEmail(request.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Departamento departamento = departamentoRepository.buscarPorId(request.departamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado"));

        Set<Role> roles = request.perfil().stream()
                .map(nomeRole -> roleRepository.buscarPorNome(nomeRole)
                        .orElseThrow(() -> new IllegalArgumentException("Perfil (Role) '" + nomeRole + "' não encontrado.")))
                .collect(Collectors.toSet());


        String senhaCriptografada = passwordEncoder.encode(request.senha());

        User novoUsuario = new User(
                request.nome(),
                request.email(),
                senhaCriptografada,
                roles,
                departamento
        );

        User usuarioSalvo = userRepository.salvar(novoUsuario);

        var userEntity = userSpringDataRepository.findById(usuarioSalvo.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado após criação"));

        var response = userDTOMapper.toResponse(userEntity);

        return response;

    }
}
