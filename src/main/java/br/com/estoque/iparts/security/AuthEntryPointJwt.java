package br.com.estoque.iparts.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * Este método é chamado sempre que um usuário não autenticado tenta acessar
     * um recurso protegido.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        logger.error("Erro de acesso não autorizado: {}", authException.getMessage());

        // Define o tipo de conteúdo da resposta como JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // Define o status da resposta como 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Cria um corpo de resposta JSON customizado
        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Não autorizado");
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());

        // Usa o ObjectMapper do Jackson (ferramenta padrão do Spring para JSON)
        // para escrever o mapa como um JSON na resposta.
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
