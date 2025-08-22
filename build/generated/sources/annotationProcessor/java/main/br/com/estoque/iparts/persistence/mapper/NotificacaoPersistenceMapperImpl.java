package br.com.estoque.iparts.persistence.mapper;

import br.com.estoque.iparts.application.domain.model.Notificacao;
import br.com.estoque.iparts.persistence.entity.NotificacaoJpaEntity;
import br.com.estoque.iparts.persistence.entity.UserJpaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T00:53:43-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class NotificacaoPersistenceMapperImpl implements NotificacaoPersistenceMapper {

    @Override
    public NotificacaoJpaEntity toEntity(Notificacao domain) {
        if ( domain == null ) {
            return null;
        }

        NotificacaoJpaEntity notificacaoJpaEntity = new NotificacaoJpaEntity();

        notificacaoJpaEntity.setFkUser( notificacaoToUserJpaEntity( domain ) );
        notificacaoJpaEntity.setId( domain.getId() );
        notificacaoJpaEntity.setTitulo( domain.getTitulo() );
        notificacaoJpaEntity.setMensagem( domain.getMensagem() );
        notificacaoJpaEntity.setData( domain.getData() );
        notificacaoJpaEntity.setStatus( domain.getStatus() );

        return notificacaoJpaEntity;
    }

    @Override
    public Notificacao toDomain(NotificacaoJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Notificacao notificacao = new Notificacao();

        notificacao.setIdUsuario( entityFkUserId( entity ) );
        notificacao.setId( entity.getId() );
        notificacao.setTitulo( entity.getTitulo() );
        notificacao.setMensagem( entity.getMensagem() );
        notificacao.setStatus( entity.getStatus() );
        notificacao.setData( entity.getData() );

        return notificacao;
    }

    protected UserJpaEntity notificacaoToUserJpaEntity(Notificacao notificacao) {
        if ( notificacao == null ) {
            return null;
        }

        UserJpaEntity userJpaEntity = new UserJpaEntity();

        userJpaEntity.setId( notificacao.getIdUsuario() );

        return userJpaEntity;
    }

    private Integer entityFkUserId(NotificacaoJpaEntity notificacaoJpaEntity) {
        if ( notificacaoJpaEntity == null ) {
            return null;
        }
        UserJpaEntity fkUser = notificacaoJpaEntity.getFkUser();
        if ( fkUser == null ) {
            return null;
        }
        Integer id = fkUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
