package br.com.estoque.iparts.infrastructure.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartamentoRequest(
        @NotBlank(message = "O nome não pode ser vazio")
        String nomeDepartamento
) {
}
