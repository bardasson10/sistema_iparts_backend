package br.com.estoque.iparts.application.ports.out;

import br.com.estoque.iparts.application.domain.model.Notificacao;

import java.util.List;

public interface NotificacaoRepositoryPort {
    Notificacao salvar(Notificacao notificacao);
    List<Notificacao> buscarPorUsuarioId(Integer usuarioId);
}
