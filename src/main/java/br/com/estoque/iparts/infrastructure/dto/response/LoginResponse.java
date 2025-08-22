package br.com.estoque.iparts.infrastructure.dto.response;

public record LoginResponse(
        Integer id,
        String nome,
        String email,
        String token
) {
}
