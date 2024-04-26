package com.example.library_management_system.security;

import com.example.library_management_system.security.jwt.JwtAuthFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private AuthenticationProvider authenticationProvider;
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    public SecurityConfig(AuthenticationProvider authenticationProvider,JwtAuthFilter jwtAuthFilter){
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        auth->auth
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/users").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exp->
                        exp.authenticationEntryPoint(
                                ((request, response, authException) -> {
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
                                })
                        )
                )
                .build();
    }
}
