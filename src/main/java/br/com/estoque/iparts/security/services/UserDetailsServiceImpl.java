package br.com.estoque.iparts.security.services;

import br.com.estoque.iparts.application.ports.out.UserRepositoryPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoryPort userRepository;

    public UserDetailsServiceImpl(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.buscarPorEmail(email) // <-- Adicione `buscarPorEmail` ao seu UserRepositoryPort/Adapter
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        // Convertemos nosso usuário de domínio para o UserDetails do Spring
        return UserDetailsImpl.build(user);
    }
}
