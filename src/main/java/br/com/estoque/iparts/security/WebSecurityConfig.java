// Geralmente no pacote infrastructure/security ou config/security
package br.com.estoque.iparts.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // Supondo que você tenha um filtro de JWT chamado AuthTokenFilter
    // @Autowired
    // private AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita o CSRF, pois não usamos sessões/cookies para autenticação
                .csrf(csrf -> csrf.disable())

                // 2. Define a política de sessão como STATELESS, essencial para APIs REST com JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Configura as regras de autorização para cada endpoint
                .authorizeHttpRequests(authorize -> authorize
                        // Libera endpoints de autenticação e registro
                        // .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v0/users/create").permitAll() // Libera a criação de usuários

                        // Libera todos os endpoints necessários para o Swagger/OpenAPI
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // 4. Exige autenticação para todas as outras requisições
                        .anyRequest().authenticated()
                );

        // 5. Adiciona seu filtro de JWT para ser executado antes do filtro padrão do Spring
        // http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorna um PasswordEncoder padrão, como BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }
}