package com.sumsum.ai.config;

import com.sumsum.ai.service.CustomOauth2UserService;
import com.sumsum.ai.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOauth2UserService customOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/", "/css/**", "/images/**", "/js/**")
                .permitAll()
                .requestMatchers("api/v1/**")
                .hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .oauth2Login()
                .successHandler(((request, response, authentication) -> response.sendRedirect("http://localhost:3000")))
                .userInfoEndpoint()
                .userService(customOauth2UserService);
        return http.build();
    }


}