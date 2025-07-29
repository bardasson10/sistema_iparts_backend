package br.com.estoque.iparts.infrastructure.dto.response;

import br.com.estoque.iparts.security.enums.StatusEnum;

import java.util.Set;

public record UserReponse(
        Integer id,
        String nome,
        String email,
        StatusEnum status,
        DepartamentoResponse departamento,
        Set<String> roles
) { }
