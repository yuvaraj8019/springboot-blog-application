package com.app.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.app.blog.security.JwtAuthenticationEntryPoint;
import com.app.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity
public class SecurityConfig {
	
	private final String[] NOAUTH_ALLOWED_URLS = { "/api/v1/auth/**", "/v3/api-docs/**", "/v2/api-docs", "/swagger-ui/**", "/swagger-ui.html" }; 
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        		.authorizeHttpRequests(auth -> auth
        				.requestMatchers(NOAUTH_ALLOWED_URLS).permitAll()
                .anyRequest()
                .authenticated())
        		.exceptionHandling(entrypoint -> entrypoint.authenticationEntryPoint(authenticationEntryPoint))
        		.sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
	
}
