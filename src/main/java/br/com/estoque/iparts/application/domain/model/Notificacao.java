package br.com.estoque.iparts.application.domain.model;

import br.com.estoque.iparts.security.enums.RoleEnum;
import br.com.estoque.iparts.security.enums.StatusEnum;

import java.time.LocalDateTime;

public class Notificacao {
    private Integer id;
    private String titulo;
    private String mensagem;
    private StatusEnum status;
    private RoleEnum tipoDeUsuario;
    private LocalDateTime data ;
    private Integer idUsuario;

    public Notificacao() {
    }

    public Notificacao(String titulo, String mensagem, StatusEnum status, RoleEnum tipoDeUsuario, Integer  IdUsuario) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.status = status;
        this.tipoDeUsuario = tipoDeUsuario;
        this.data = LocalDateTime.now();
        this.idUsuario = IdUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public RoleEnum getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(RoleEnum tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
