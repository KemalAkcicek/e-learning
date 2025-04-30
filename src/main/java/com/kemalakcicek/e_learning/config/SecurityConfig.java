package com.kemalakcicek.e_learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kemalakcicek.e_learning.jwt.AuthEntryPoint;
import com.kemalakcicek.e_learning.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public static final String REGISTER = "/register";
	public static final String AUTHENTICATE = "/authenticate";
	public static final String REFRESH_TOKEN = "/refreshtoken";

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JWTAuthenticationFilter authenticationFilter;

	@Autowired
	private AuthEntryPoint authEntryPoint;

	public static final String[] SWAGGER_PATHS = {

			"/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html" };

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeHttpRequests(request -> request.requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN)
						.permitAll().requestMatchers(SWAGGER_PATHS).permitAll().requestMatchers("/api/student/**")
						.hasRole("STUDENT").requestMatchers("/api/admin/**").hasRole("ADMIN")
						.requestMatchers("/api/instructor/**").hasRole("INSTRUCTOR").requestMatchers("/api/boss/**")
						.hasRole("BOSS").anyRequest().authenticated())
				.exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

}
