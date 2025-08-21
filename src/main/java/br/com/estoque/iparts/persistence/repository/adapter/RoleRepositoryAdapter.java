package br.com.estoque.iparts.persistence.repository.adapter;

import br.com.estoque.iparts.application.domain.model.Role;
import br.com.estoque.iparts.application.ports.out.RoleRepositositoryPort;
import br.com.estoque.iparts.persistence.mapper.RolePersistenceMapper;
import br.com.estoque.iparts.persistence.repository.jpa.RoleSpringDataRepository;
import br.com.estoque.iparts.security.enums.RoleEnum;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleRepositoryAdapter  implements RoleRepositositoryPort {

    private final RoleSpringDataRepository jpaRepository;
    private final RolePersistenceMapper mapper;

    public RoleRepositoryAdapter(RoleSpringDataRepository jpaRepository, RolePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Role salvar(Role role) {
        var roleEntity = mapper.toEntity(role);
        var savedRole = jpaRepository.save(roleEntity);
        return mapper.toDomain(savedRole);
    }

    // Lógica simplificada e correta
    @Override
    public boolean existePorNome(String nomeRole) {
        try {
            return jpaRepository.existsByName(RoleEnum.valueOf(nomeRole.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Lógica simplificada e correta
    @Override
    public Optional<Role> buscarPorNome(String nomeRole) {
        try {
            return jpaRepository.findByName(RoleEnum.valueOf(nomeRole.toUpperCase()))
                    .map(mapper::toDomain);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
