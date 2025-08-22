package br.com.estoque.iparts.application.service.user;

import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.application.ports.in.user.LoginUserUseCase;
import br.com.estoque.iparts.application.ports.out.JwtProviderPort;
import br.com.estoque.iparts.application.ports.out.UserRepositoryPort;
import br.com.estoque.iparts.infrastructure.dto.request.LoginRequest;
import br.com.estoque.iparts.infrastructure.dto.response.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginUserServiceImpl implements LoginUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtProviderPort jwtProvider;
    private final UserRepositoryPort userRepository;

    public LoginUserServiceImpl(AuthenticationManager authenticationManager,
                                JwtProviderPort jwtProvider,
                                UserRepositoryPort userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }


    @Override
    public LoginResponse executar(LoginRequest loginRequest) {
        // 1. Cria um objeto de autenticação com as credenciais recebidas
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha()));

        // 2. Se a autenticação for bem-sucedida, define o contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Busca nosso usuário de domínio para obter outros dados
        User user = userRepository.buscarPorEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado após autenticação."));

        // 4. Gera o token JWT
        String jwt = jwtProvider.generateToken(user);

        // 5. Retorna o DTO de resposta com o token
        return new LoginResponse(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                jwt);
    }

}
