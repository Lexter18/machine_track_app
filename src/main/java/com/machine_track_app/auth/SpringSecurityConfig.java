package com.machine_track_app.auth;

import com.machine_track_app.auth.filters.JwtAuthenticationFilter;
import com.machine_track_app.auth.filters.JwtValidationFilter;

import com.machine_track_app.enums.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class SpringSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    public SpringSecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authRules -> authRules
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/owner").hasRole(RolesEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/owner/**")
                        .hasAnyRole(RolesEnum.OWNER.name(), RolesEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/byOwner").hasRole(RolesEnum.OWNER.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/user").hasRole(RolesEnum.OWNER.name())

                        .requestMatchers(HttpMethod.GET, "/api/v1/users/roles")
                        .hasAnyRole(RolesEnum.OWNER.name(), RolesEnum.ADMIN.name())

                        .requestMatchers(HttpMethod.GET, "/api/v1/states")
                        .hasAnyRole(RolesEnum.OWNER.name(), RolesEnum.ADMIN.name())

                        .requestMatchers(HttpMethod.GET, "/api/v1/users/owners").hasRole(RolesEnum.ADMIN.name())

                        .requestMatchers(HttpMethod.GET, "/api/v1/owners").hasRole(RolesEnum.ADMIN.name())

                        .requestMatchers(HttpMethod.GET, "/api/v1/locations/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/users/initialRegistration").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigurationSource;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> bean =
                new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
