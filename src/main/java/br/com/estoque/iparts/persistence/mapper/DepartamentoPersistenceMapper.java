package br.com.estoque.iparts.persistence.mapper;

import br.com.estoque.iparts.application.domain.model.Departamento;
import br.com.estoque.iparts.persistence.entity.DepartamentoJpaEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DepartamentoPersistenceMapper {

    DepartamentoJpaEntity toEntity(Departamento domain);

    Departamento toDomain(DepartamentoJpaEntity entity);
}
