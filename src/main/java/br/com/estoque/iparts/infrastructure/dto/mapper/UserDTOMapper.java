package br.com.estoque.iparts.infrastructure.dto.mapper;


import br.com.estoque.iparts.application.domain.model.Departamento;
import br.com.estoque.iparts.infrastructure.dto.response.DepartamentoResponse;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;
import br.com.estoque.iparts.persistence.entity.DepartamentoJpaEntity;
import br.com.estoque.iparts.persistence.entity.RoleJpaEntity;
import br.com.estoque.iparts.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { DepartamentoDTOMapper.class })
public interface UserDTOMapper { // 2. Transformado em interface


    @Mapping(source = "fkDepartamento", target = "departamento")
    @Mapping(source = "roles", target = "perfis")
    UserResponse toResponse(UserJpaEntity entity);

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