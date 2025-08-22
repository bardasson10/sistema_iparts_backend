// Geralmente no pacote infrastructure/security ou config/security
package br.com.estoque.iparts.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

    private final AuthTokenFilter authTokenFilter;
    private final AuthEntryPointJwt unauthorizedHandler;

    public WebSecurityConfig(AuthTokenFilter authTokenFilter, AuthEntryPointJwt unauthorizedHandler) {
        this.authTokenFilter = authTokenFilter;
        this.unauthorizedHandler = unauthorizedHandler;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita o CSRF, pois não usamos sessões/cookies para autenticação
                .csrf(csrf -> csrf.disable())

                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                // 2. Define a política de sessão como STATELESS, essencial para APIs REST com JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Configura as regras de autorização para cada endpoint
                .authorizeHttpRequests(authorize -> authorize
                        // Libera endpoints de autenticação e registro
                        // .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "autenticacao/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/v0/users/create").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/v0/users/update/**").authenticated()
                        .requestMatchers(HttpMethod.GET,"/v0/users/search/**").authenticated()



                        // Libera todos os endpoints necessários para o Swagger/OpenAPI
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // 4. Exige autenticação para todas as outras requisições
                        .anyRequest().authenticated()


                );

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorna um PasswordEncoder padrão, como BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }
}