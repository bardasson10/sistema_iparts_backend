package br.com.estoque.iparts.persistence.mapper;


import br.com.estoque.iparts.application.domain.model.User;
import br.com.estoque.iparts.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        // Adicione o novo mapper aqui
        uses = { DepartamentoPersistenceMapper.class, RolePersistenceMapper.class },
        // Política para não reclamar de campos não mapeados (como 'notificacoes')
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserPersistenceMapper {
    // Mapeando Departamento e Roles para a entidade
    @Mapping(source = "departamento", target = "fkDepartamento.id")
    @Mapping(source = "roles", target = "roles")
    UserJpaEntity toEntity(User domain);

    // Mapeando Departamento e Roles de volta para o domínio
    @Mapping(source = "fkDepartamento.id", target = "departamento")
    @Mapping(source = "roles", target = "roles")
    User toDomain(UserJpaEntity entity);

}
