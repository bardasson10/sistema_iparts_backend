package br.com.estoque.iparts.persistence.mapper;

import br.com.estoque.iparts.application.domain.model.Departamento;
import br.com.estoque.iparts.persistence.entity.DepartamentoJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T00:53:43-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class DepartamentoPersistenceMapperImpl implements DepartamentoPersistenceMapper {

    @Override
    public DepartamentoJpaEntity toEntity(Departamento domain) {
        if ( domain == null ) {
            return null;
        }

        DepartamentoJpaEntity departamentoJpaEntity = new DepartamentoJpaEntity();

        departamentoJpaEntity.setId( domain.getId() );
        departamentoJpaEntity.setNome( domain.getNome() );

        return departamentoJpaEntity;
    }

    @Override
    public Departamento toDomain(DepartamentoJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Departamento departamento = new Departamento();

        departamento.setId( entity.getId() );
        departamento.setNome( entity.getNome() );

        return departamento;
    }
}
