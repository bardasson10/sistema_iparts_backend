package br.com.estoque.iparts.persistence.repository.adapter;

import br.com.estoque.iparts.application.domain.model.Notificacao;
import br.com.estoque.iparts.application.ports.out.NotificacaoRepositoryPort;
import br.com.estoque.iparts.persistence.mapper.NotificacaoPersistenceMapper;
import br.com.estoque.iparts.persistence.repository.jpa.NotificacaoSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NotifcacaoRepositoryAdapater  implements NotificacaoRepositoryPort {

    @Autowired
    private NotificacaoSpringDataRepository jpaRepository;

    @Autowired
    private NotificacaoPersistenceMapper mapper;

    @Override
    public Notificacao salvar(Notificacao notificacao) {
        var notificacaoEntity = mapper.toEntity(notificacao);
        var savedNotificacao = jpaRepository.save(notificacaoEntity);
        return mapper.toDomain(savedNotificacao);
    }

    @Override
    public List<Notificacao> buscarPorUsuarioId(Integer usuarioId) {
        return jpaRepository.findByFkUser_Id(usuarioId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
