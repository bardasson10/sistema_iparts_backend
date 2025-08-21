package br.com.estoque.iparts.infrastructure.configs;

import br.com.estoque.iparts.application.ports.out.DepartamentoRepositoryPort;
import br.com.estoque.iparts.application.ports.out.PasswordEncoderPort;
import br.com.estoque.iparts.application.ports.out.RoleRepositositoryPort;
import br.com.estoque.iparts.application.ports.out.UserRepositoryPort;
import br.com.estoque.iparts.application.service.CreateUserServiceImpl;
import br.com.estoque.iparts.infrastructure.dto.mapper.UserDTOMapper;
import br.com.estoque.iparts.persistence.repository.jpa.UserSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public CreateUserServiceImpl createUserService(
            UserRepositoryPort userRepository,
            DepartamentoRepositoryPort departamentoRepository,
            RoleRepositositoryPort roleRepository,
            PasswordEncoderPort passwordEncoder,
            UserDTOMapper userDTOMapper,
            UserSpringDataRepository userSpringDataRepository
    ) {
        return new CreateUserServiceImpl(
                userRepository,
                departamentoRepository,
                roleRepository,
                passwordEncoder,
                userDTOMapper,
                userSpringDataRepository);
    }

}
