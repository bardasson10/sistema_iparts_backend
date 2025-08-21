package br.com.estoque.iparts.application.service;


import br.com.estoque.iparts.application.domain.model.Departamento;
import br.com.estoque.iparts.application.domain.model.Role;
import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.application.ports.in.CreateUserUseCase;
import br.com.estoque.iparts.application.ports.out.DepartamentoRepositoryPort;
import br.com.estoque.iparts.application.ports.out.RoleRepositositoryPort;
import br.com.estoque.iparts.application.ports.out.UserRepositoryPort;
import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;
import br.com.estoque.iparts.security.enums.RoleEnum;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;


public class CreateUserServiceImpl  implements CreateUserUseCase {

    private final UserRepositoryPort userRepository;
    private final DepartamentoRepositoryPort departamentoRepository;
    private final RoleRepositositoryPort  roleRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUserServiceImpl(UserRepositoryPort userRepository, DepartamentoRepositoryPort departamentoRepository, RoleRepositositoryPort roleRepository) {
        this.userRepository = userRepository;
        this.departamentoRepository = departamentoRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User executar(CreateUserRequest request) {
        if (userRepository.existePorEmail(request.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Departamento departamento = departamentoRepository.buscarPorId(request.departamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado"));

        Set<Role> roles = request.perfil().stream()
                .map(nomeRole -> roleRepository.buscarPorNome(nomeRole)
                        .orElseThrow(() -> new IllegalArgumentException("Perfil (Role) '" + nomeRole + "' não encontrado.")))
                .collect(Collectors.toSet());


//        String senhaCriptografada = passwordEncoder.encode(request.senha());

        User novoUsuario = new User(
                request.nome(),
                request.email(),
                request.senha(), // Aqui você deve usar passwordEncoder.encode(request.senha()) se estiver usando criptografia
                roles,
                departamento
        );

        return userRepository.salvar(novoUsuario);
    }
}
