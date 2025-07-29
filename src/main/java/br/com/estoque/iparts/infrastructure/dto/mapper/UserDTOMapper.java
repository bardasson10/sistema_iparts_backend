package br.com.estoque.iparts.infrastructure.dto.mapper;

import br.com.estoque.iparts.infrastructure.dto.response.UserReponse;
import br.com.estoque.iparts.persistence.entity.RoleJpaEntity;
import br.com.estoque.iparts.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { DepartamentoDTOMapper.class })
public interface UserDTOMapper { // 2. Transformado em interface

    // 3. Mapeamento corrigido para usar a fonte 'fkDepartamento'
    @Mapping(source = "fkDepartamento", target = "departamento")
    @Mapping(source = "roles", target = "roles")
    UserReponse toResponse(UserJpaEntity entity); // 4. Sem corpo de método!

    // O método auxiliar para as roles continua igual
    default Set<String> mapRoles(Set<RoleJpaEntity> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(role -> role.getName().toString())
                .collect(Collectors.toSet());
    }
}