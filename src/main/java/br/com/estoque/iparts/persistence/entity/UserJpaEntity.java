package br.com.estoque.iparts.persistence.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.com.estoque.iparts.security.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "users")
public class UserJpaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_cd_id")
	private Integer id;

	@NotBlank
	@Column(name = "user_tx_nome")
	private String nome;

	@NotBlank
	@Column(name = "user_tx_email")
	private String email;

	@NotBlank
	@Column(name = "user_tx_senha ")
	private String senha;
	
	@Column(name = "user_dt_data_ultima_senha")
	private LocalDateTime dataUltimaSenha;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleJpaEntity> roles = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "departamento_cd_id")
	private DepartamentoJpaEntity fkDepartamento;

	@Column(name="user_enum_status")
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	@OneToMany(mappedBy = "fkUser")
	private List<NotificacaoJpaEntity> notificacoes;


	public UserJpaEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public @NotBlank String getNome() {
		return nome;
	}

	public void setNome(@NotBlank String nome) {
		this.nome = nome;
	}

	public @NotBlank String getEmail() {
		return email;
	}

	public void setEmail(@NotBlank String email) {
		this.email = email;
	}

	public @NotBlank String getSenha() {
		return senha;
	}

	public void setSenha(@NotBlank String senha) {
		this.senha = senha;
	}

	public LocalDateTime getDataUltimaSenha() {
		return dataUltimaSenha;
	}

	public void setDataUltimaSenha(LocalDateTime dataUltimaSenha) {
		this.dataUltimaSenha = dataUltimaSenha;
	}

	public Set<RoleJpaEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleJpaEntity> roles) {
		this.roles = roles;
	}

	public DepartamentoJpaEntity getFkDepartamento() {
		return fkDepartamento;
	}

	public void setFkDepartamento(DepartamentoJpaEntity fkDepartamento) {
		this.fkDepartamento = fkDepartamento;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public List<NotificacaoJpaEntity> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<NotificacaoJpaEntity> notificacoes) {
		this.notificacoes = notificacoes;
	}
}