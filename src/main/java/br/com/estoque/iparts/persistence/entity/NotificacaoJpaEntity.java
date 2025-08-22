package br.com.estoque.iparts.persistence.entity;

import java.time.LocalDateTime;

import br.com.estoque.iparts.security.enums.RoleEnum;
import br.com.estoque.iparts.security.enums.StatusEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "notificacoes")
public class NotificacaoJpaEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificacao_cd_id")
	private Integer id;

	@Column(name = "notificacao_tx_titulo")
	private String titulo;

	@Column(name = "notificacao_tx_mensagem")
	private String mensagem;

	@Column(name="notificacao_enum_status")
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	@Column(name="notificacao_enum_tipo_user")
	@Enumerated(EnumType.STRING)
	private RoleEnum tipoUser;

	@Column(name = "notificacao_dt_data")
	private LocalDateTime data = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_user")
	private UserJpaEntity fkUser;



	public NotificacaoJpaEntity() {
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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public UserJpaEntity getFkUser() {
		return fkUser;
	}

	public void setFkUser(UserJpaEntity fkUser) {
		this.fkUser = fkUser;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public RoleEnum getTipoUser() {
		return tipoUser;
	}

	public void setTipoUser(RoleEnum tipoUser) {
		this.tipoUser = tipoUser;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
}
