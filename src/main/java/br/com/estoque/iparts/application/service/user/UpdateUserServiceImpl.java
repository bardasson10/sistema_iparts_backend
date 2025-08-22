package br.com.estoque.iparts.application.service.user;

import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.application.ports.in.user.UpdateUserUseCase;
import br.com.estoque.iparts.application.ports.out.DepartamentoRepositoryPort;
import br.com.estoque.iparts.application.ports.out.PasswordEncoderPort;
import br.com.estoque.iparts.application.ports.out.RoleRepositositoryPort;
import br.com.estoque.iparts.application.ports.out.UserRepositoryPort;
import br.com.estoque.iparts.infrastructure.dto.mapper.UserDTOMapper;
import br.com.estoque.iparts.infrastructure.dto.request.UpdateUserRequest;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;
import br.com.estoque.iparts.persistence.repository.jpa.UserSpringDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class UpdateUserServiceImpl  implements UpdateUserUseCase {

    private final UserRepositoryPort  userRepository;
    private  final UserDTOMapper userDTOMapper;
    private final UserSpringDataRepository userJpaRepository;
    private  final PasswordEncoderPort passwordEncoder;
    private final DepartamentoRepositoryPort departamentoRepository;
    private final RoleRepositositoryPort roleRepository;

    public UpdateUserServiceImpl(
            UserRepositoryPort userRepository,
            UserDTOMapper userDTOMapper,
            UserSpringDataRepository userJpaRepository,
            PasswordEncoderPort passwordEncoder,
            DepartamentoRepositoryPort departamentoRepository,
            RoleRepositositoryPort roleRepository) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.departamentoRepository = departamentoRepository;
        this.roleRepository = roleRepository;

    }

    @Override
    @Transactional
    public UserResponse executar(Integer id, Boolean isAtivo, UpdateUserRequest request) {

        User usuarioAtual = userRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado."));


        if (request.nome() != null) {
            usuarioAtual.setNome(request.nome());
        }

        if (request.email() != null && !request.email().equals(usuarioAtual.getEmail())) {
            if (userRepository.existePorEmail(request.email())) {
                throw new RuntimeException("Email já está em uso.");
            }
            usuarioAtual.setEmail(request.email());
        }

        if (request.senha() != null) {
            String novaSenhaCriptografada = passwordEncoder.encode(request.senha());
            usuarioAtual.alterarSenha(novaSenhaCriptografada);
        }

        if (isAtivo != null) {
            if(! isAtivo){
                usuarioAtual.inativar();
            } else {
                usuarioAtual.ativar();
            }
        }

        if (request.departamentoId() != null) {
            var departamento = departamentoRepository.buscarPorId(request.departamentoId())
                    .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado"));
            usuarioAtual.setDepartamento(departamento);
        }

        if (request.perfil() != null && !request.perfil().isEmpty()) {
            var roles = request.perfil().stream()
                    .map(nomeRole -> roleRepository.buscarPorNome(nomeRole)
                            .orElseThrow(() -> new IllegalArgumentException("Perfil '" + nomeRole + "' não encontrado.")))
                    .collect(Collectors.toSet());
            usuarioAtual.setRoles(roles);
        }


        User usuarioSalvo =  userRepository.salvar(usuarioAtual);

        var userEntity = userJpaRepository.findById(usuarioSalvo.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + usuarioSalvo.getId()));

        var response = userDTOMapper.toResponse(userEntity);

        return response;

    }
}
