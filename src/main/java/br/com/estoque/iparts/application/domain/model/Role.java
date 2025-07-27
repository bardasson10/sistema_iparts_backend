package br.com.estoque.iparts.application.domain.model;

import br.com.estoque.iparts.security.enums.RoleEnum;

public class Role {
    private  Integer id;
    private RoleEnum nome;

    public Role() {
    }

    public Role(RoleEnum nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public RoleEnum getNome() {
        return nome;
    }

    public void setNome(RoleEnum nome) {
        this.nome = nome;
    }
}
