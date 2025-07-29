package br.com.estoque.iparts.persistence.mapper;

import br.com.estoque.iparts.application.domain.model.Notificacao;
import br.com.estoque.iparts.persistence.entity.NotificacaoJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificacaoPersistenceMapper {
    /**
     * Converte o domínio para a entidade JPA.
     * Pega o 'usuarioId' do domínio e o coloca dentro do objeto 'fkUser' da entidade.
     */
    @Mapping(source = "idUsuario", target = "fkUser.id")
    NotificacaoJpaEntity toEntity(Notificacao domain);

    /**
     * Converte a entidade JPA para o domínio.
     * Pega o 'id' de dentro do objeto 'fkUser' da entidade e o atribui a 'usuarioId' no domínio.
     */
    @Mapping(source = "fkUser.id", target = "idUsuario")
    Notificacao toDomain(NotificacaoJpaEntity entity);
}
