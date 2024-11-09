package com.book.Library.config;

import com.book.Library.security.JwtTokenFilter;
import com.book.Library.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Отключаем CSRF для упрощения
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/v3/api-docs/**").permitAll() // Разрешаем доступ к Swagger API
                        .requestMatchers("/swagger-ui/**").permitAll() // Разрешаем доступ к Swagger UI
                        .requestMatchers("/api/auth/register").permitAll() // Разрешаем доступ к регистрации
                        .requestMatchers("/api/auth/login").permitAll() // Разрешаем доступ к авторизации
                        .requestMatchers("/api/auth/refresh").permitAll() // Разрешаем доступ к авторизации
                        .requestMatchers("/api/library/readers/unreturned").permitAll() // Оставляем открытым
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
