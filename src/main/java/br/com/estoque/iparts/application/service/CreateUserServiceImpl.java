package br.com.estoque.iparts.application.service;


import br.com.estoque.iparts.application.domain.model.Departamento;
import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.application.ports.in.CreateUserUseCase;
import br.com.estoque.iparts.application.ports.out.DepartamentoRepositoryPort;
import br.com.estoque.iparts.application.ports.out.UserRepositoryPort;
import br.com.estoque.iparts.infrastructure.dto.request.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;


public class CreateUserServiceImpl  implements CreateUserUseCase {

    private final UserRepositoryPort userRepository;
    private final DepartamentoRepositoryPort departamentoRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUserServiceImpl(UserRepositoryPort userRepository, DepartamentoRepositoryPort departamentoRepository) {
        this.userRepository = userRepository;
        this.departamentoRepository = departamentoRepository;
    }


    @Override
    public User executar(CreateUserRequest request) {
        if (userRepository.existePorEmail(request.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Departamento departamento = departamentoRepository.buscarPorId(request.departamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado"));


        String senhaCriptografada = passwordEncoder.encode(request.senha());

        User novoUsuario = new User(
                request.nome(),
                request.email(),
                senhaCriptografada,
                request.perfil(),
                departamento.getId()
        );

        return userRepository.salvar(novoUsuario);
    }
}
