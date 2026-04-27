package co.edu.distrital.fis.vivienda_backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Configuramos CORS llamando al método de abajo
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // 2. Desactivamos CSRF porque usaremos JWT
            .csrf(csrf -> csrf.disable())
            
            // 3. Configuramos las rutas públicas y privadas
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**").permitAll() // Login y registro son públicos
                .anyRequest().authenticated() // Todo lo demás requiere token
            )
            
            // 4. Le decimos a Spring que no guarde sesiones en memoria (Stateless)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // 5. Agregamos nuestro proveedor y el filtro de JWT
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Bean maestro para derrotar al error de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permitimos que tu frontend de React (Vite) se conecte
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        
        // Permitimos todos los métodos HTTP, incluyendo el OPTIONS (Preflight)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        
        // Permitimos que el frontend envíe el Token en la cabecera "Authorization"
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        
        // Permitimos el envío de credenciales
        configuration.setAllowCredentials(true);

        // Aplicamos esta regla a todas las rutas de nuestra API
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}