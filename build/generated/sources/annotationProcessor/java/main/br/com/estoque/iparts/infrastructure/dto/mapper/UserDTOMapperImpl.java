package br.com.estoque.iparts.infrastructure.dto.mapper;

import br.com.estoque.iparts.infrastructure.dto.response.DepartamentoResponse;
import br.com.estoque.iparts.infrastructure.dto.response.UserResponse;
import br.com.estoque.iparts.persistence.entity.UserJpaEntity;
import br.com.estoque.iparts.security.enums.StatusEnum;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T00:53:42-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserDTOMapperImpl implements UserDTOMapper {

    @Autowired
    private DepartamentoDTOMapper departamentoDTOMapper;

    @Override
    public UserResponse toResponse(UserJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        DepartamentoResponse departamento = null;
        Set<String> perfis = null;
        Integer id = null;
        String nome = null;
        String email = null;
        StatusEnum status = null;

        departamento = departamentoDTOMapper.toResponse( entity.getFkDepartamento() );
        perfis = mapRoles( entity.getRoles() );
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        status = entity.getStatus();

        UserResponse userResponse = new UserResponse( id, nome, email, status, departamento, perfis );

        return userResponse;
    }
}
