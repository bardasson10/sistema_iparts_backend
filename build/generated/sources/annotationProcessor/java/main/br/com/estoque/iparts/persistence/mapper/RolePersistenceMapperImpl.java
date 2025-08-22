package br.com.estoque.iparts.persistence.mapper;

import br.com.estoque.iparts.application.domain.model.Role;
import br.com.estoque.iparts.persistence.entity.RoleJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T00:53:43-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class RolePersistenceMapperImpl implements RolePersistenceMapper {

    @Override
    public Role toDomain(RoleJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Role role = new Role();

        role.setNome( entity.getName() );
        role.setId( entity.getId() );

        return role;
    }

    @Override
    public RoleJpaEntity toEntity(Role domain) {
        if ( domain == null ) {
            return null;
        }

        RoleJpaEntity roleJpaEntity = new RoleJpaEntity();

        roleJpaEntity.setName( domain.getNome() );
        roleJpaEntity.setId( domain.getId() );

        return roleJpaEntity;
    }
}
