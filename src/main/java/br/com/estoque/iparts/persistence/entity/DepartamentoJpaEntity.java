package br.com.estoque.iparts.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "departamento")
public class DepartamentoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departamento_cd_id")
    private Integer id;

    @NotBlank(message = "Nome do departamento é obrigatório")
    @Column(name = "departamento_tx_nome")
    private String nome;

    public DepartamentoJpaEntity() {
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

}
