package br.com.estoque.iparts.infrastructure.dto.request;


import br.com.estoque.iparts.application.domain.model.Role;
import jakarta.validation.constraints.*;

import java.util.Set;

public record CreateUserRequest(
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(max = 100)
        String nome,

        @NotBlank(message = "O email não pode ser vazio")
//        @Pattern(
//                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@$#!%*?&]{8,}$",
//                message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
//        )
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "A senha não pode ser vazia")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String senha,

        @NotEmpty(message = "O usuário deve ter pelo menos um perfil.")
        Set<String> perfil,

        @NotNull(message = "O ID do departamento é obrigatório")
        Integer departamentoId

) {
}
