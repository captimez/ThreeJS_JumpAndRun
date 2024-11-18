package de.project.web.gameserver.config;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import jakarta.servlet.http.HttpServletRequest;

@Component
public class CustomCorsConfiguration implements CorsConfigurationSource {

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();

        // Erlaubte Ursprünge
        config.setAllowedOrigins(List.of("http://localhost:5173")); // Nur explizite Ursprünge erlauben
        // Alternativ für Platzhalter:
        // config.setAllowedOriginPatterns(List.of("http://*.localhost:5173"));

        // Erlaubte Methoden
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Erlaubte Header
        config.setAllowedHeaders(List.of("*"));

        // Anmeldeinformationen (z. B. Cookies) erlauben
        config.setAllowCredentials(true);

        return config;
    }
}