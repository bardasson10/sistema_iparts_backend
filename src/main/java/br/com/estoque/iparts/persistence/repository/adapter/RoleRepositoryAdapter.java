package br.com.estoque.iparts.persistence.repository.adapter;

import br.com.estoque.iparts.application.domain.model.Role;
import br.com.estoque.iparts.application.ports.out.RoleRepositositoryPort;
import br.com.estoque.iparts.persistence.mapper.RolePersistenceMapper;
import br.com.estoque.iparts.persistence.repository.jpa.RoleSpringDataRepository;
import br.com.estoque.iparts.security.enums.RoleEnum;
import org.springframework.stereotype.Component;

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

    @Override
    public boolean existePorNome(String nomeRole) {
        try {
            // Tenta converter a String para o Enum correspondente
            RoleEnum roleEnum = RoleEnum.valueOf(nomeRole.toUpperCase());
            // Verifica se o Enum existe no repositório
            return true;
        } catch (IllegalArgumentException e) {
            // Se a String não for um Enum válido, a role obviamente não existe
            return false;
        }
    }
}
