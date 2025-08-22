package br.com.estoque.iparts.infrastructure.dto.mapper;

import br.com.estoque.iparts.infrastructure.dto.response.DepartamentoResponse;
import br.com.estoque.iparts.persistence.entity.DepartamentoJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T00:53:43-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class DepartamentoDTOMapperImpl implements DepartamentoDTOMapper {

    @Override
    public DepartamentoResponse toResponse(DepartamentoJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String nome = null;

        id = entity.getId();
        nome = entity.getNome();

        DepartamentoResponse departamentoResponse = new DepartamentoResponse( id, nome );

        return departamentoResponse;
    }
}
