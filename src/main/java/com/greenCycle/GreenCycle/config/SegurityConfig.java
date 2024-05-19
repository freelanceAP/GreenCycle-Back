package com.greenCycle.GreenCycle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//1. EnableWebSecurity -> Anotacion para configurar spring segurity
@EnableWebSecurity
public class SegurityConfig {

     private final String[] PUBLIC_RESOURCES = {"/**"};

    //3. Declarar rutas privadas
    @Bean
    //Bean -> indica que el objeto retornado por el metodo debe ser registrado como un bean (frijol) em el contexto de la app
    public SecurityFilterChain segurityFilterChain(HttpSecurity http) throws Exception{

        return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authRequest -> authRequest
        .requestMatchers(PUBLIC_RESOURCES).permitAll() //Todas las rutas publicas tendran acceso 
        .anyRequest().authenticated()//Todas las otras necesitan aunteticacion
        ).build();

    }
}
