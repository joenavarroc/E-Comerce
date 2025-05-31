package com.fretoplanton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // 🔓 Rutas públicas (ajustá según tu estructura)
                .requestMatchers("/", "/index", "/index.html", "/productos/**", "/css/**", "/js/**", "/img/**", "/favicon.ico", 
                 "/plantas-interior.html", "/plantas-exterior.html", "/macetas.html", "/souvenirs.html", 
                 "/suculentas.html", "/nosotros.html").permitAll()
                // Incluir la ruta de login y registro si existen
                .requestMatchers("/auth/login", "/auth/registro").permitAll()

                // 🔐 Rutas protegidas: solo usuarios logueados
                .requestMatchers("/carrito/**", "/comprar/**", "/usuario/**").authenticated()

                // 🔐 Cualquier otra ruta, también requiere login
                .anyRequest().authenticated()
            )
            // Configuración para el login
            .formLogin(form -> form
                .loginPage("/auth/login")  // Ruta para la página de login
                .defaultSuccessUrl("/", true)  // Redirige al index después del login
                .permitAll()
            )
            // Configuración para logout
            .logout(logout -> logout
                .logoutUrl("/auth/logout")  // Ruta para cerrar sesión
                .logoutSuccessUrl("/")  // Redirige al index después de logout
                .permitAll()
            );

        return http.build();
    }
}
