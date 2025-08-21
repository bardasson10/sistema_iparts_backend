package br.com.estoque.iparts.infrastructure.dto.mapper;

import br.com.estoque.iparts.infrastructure.dto.response.DepartamentoResponse;
import br.com.estoque.iparts.persistence.entity.DepartamentoJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartamentoDTOMapper {

    DepartamentoResponse toResponse(DepartamentoJpaEntity entity);
}
