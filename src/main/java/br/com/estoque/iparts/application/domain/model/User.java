package br.com.estoque.iparts.application.domain.model;

import br.com.estoque.iparts.security.enums.RoleEnum;
import br.com.estoque.iparts.security.enums.StatusEnum;
import java.time.LocalDateTime;
import java.util.Set;

public class User {

    private  Integer id;
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataUltimaSenha;
    private StatusEnum status;
    private Set<Role> roles;
    private Departamento departamento;

    public User(String nome, String email, String senha, Set<Role> roles, Departamento departamento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataUltimaSenha = LocalDateTime.now();
        this.roles = roles;
        this.departamento= departamento;
        this.status = StatusEnum.inativo;
    }

    //logica de negocio

    public void  inativar() {
        if (this.status == StatusEnum.inativo) {
            throw new RuntimeException("Usuario ja esta inativo");
        }
        this.status = StatusEnum.inativo;
    }

    public void alterarSenha(String novaSenhaCripografada){
        this.senha = novaSenhaCripografada;
        this.dataUltimaSenha = LocalDateTime.now();
    }

    public  boolean precisaTrocarSenha(){
        return this.dataUltimaSenha.isBefore(LocalDateTime.now().minusMonths(3));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataUltimaSenha() {
        return dataUltimaSenha;
    }

    public void setDataUltimaSenha(LocalDateTime dataUltimaSenha) {
        this.dataUltimaSenha = dataUltimaSenha;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamentoepartamento) {
        this.departamento = departamentoepartamento;
    }
}
