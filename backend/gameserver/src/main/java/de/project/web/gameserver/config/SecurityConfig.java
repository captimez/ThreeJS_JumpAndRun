package de.project.web.gameserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired CustomCorsConfiguration webConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // CSRF für WebSocket deaktivieren
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/ws/**").permitAll() // WebSocket-Endpunkt erlauben
                        .anyRequest().permitAll()) // Alle anderen Anfragen erlauben
                .cors(c -> c.configurationSource(webConfiguration)); // CORS-Konfiguration anwenden
        return httpSecurity.build();
    }
}