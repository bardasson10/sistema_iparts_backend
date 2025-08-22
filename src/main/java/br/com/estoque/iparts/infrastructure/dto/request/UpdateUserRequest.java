package br.com.estoque.iparts.infrastructure.dto.request;

import br.com.estoque.iparts.security.enums.StatusEnum;
import jakarta.validation.constraints.*;

import java.util.Set;

public record UpdateUserRequest(
        @Size(max = 100)
        String nome,

        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@$#!%*?&]{8,}$",
                message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
        )
        @Email(message = "Formato de email inválido")
        String email,

        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String senha,

        Set<String> perfil,

        Integer departamentoId


) {
}
