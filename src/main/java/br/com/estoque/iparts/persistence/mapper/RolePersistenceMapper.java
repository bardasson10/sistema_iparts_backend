package br.com.estoque.iparts.persistence.mapper;

import br.com.estoque.iparts.application.domain.model.Role;
import br.com.estoque.iparts.persistence.entity.RoleJpaEntity;
import br.com.estoque.iparts.security.enums.RoleEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {


    // 2. Mapeia o campo "name" da entidade para "nome" no domínio
    @Mapping(source = "name", target = "nome")
    Role toDomain(RoleJpaEntity entity);

    // 3. Mapeia o campo "nome" do domínio para "name" na entidade
    @Mapping(source = "nome", target = "name")
    RoleJpaEntity toEntity(Role domain);

}
