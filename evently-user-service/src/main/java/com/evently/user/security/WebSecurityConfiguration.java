package com.evently.user.security;

import com.evently.user.exception.FilterChainExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final FilterChainExceptionHandler filterChainExceptionHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http, FilterChainExceptionHandler filterChainExceptionHandler) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
//                    request.requestMatchers("/api/v1/admin/**").hasAuthority("role:admin");
//                    request.requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority("admin:delete");
//                    request.requestMatchers(PUT, "/api/v1/admin/**").hasAuthority("admin:update");
//
//                    request.requestMatchers(POST, "/api/v1/registrations/**").permitAll();
                    //request.requestMatchers("/api").authenticated();

                    request.anyRequest().permitAll();
                })
                .sessionManagement(management -> {
                    management.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(filterChainExceptionHandler, LogoutFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
