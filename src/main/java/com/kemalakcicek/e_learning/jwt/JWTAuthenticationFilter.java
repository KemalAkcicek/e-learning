package com.kemalakcicek.e_learning.jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kemalakcicek.e_learning.exception.BaseException;
import com.kemalakcicek.e_learning.exception.ErrorMessage;
import com.kemalakcicek.e_learning.exception.MessageType;
import com.kemalakcicek.e_learning.handler.SecurityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header == null) {
			filterChain.doFilter(request, response);
			return;
		}

		String token;

		String username;

		token = header.substring(7);

		try {

			username = jwtService.getUserNameByToken(token);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				if (userDetails != null && jwtService.isTokenValid(token)) {

					List<SimpleGrantedAuthority> authorities = jwtService.getRolesFromToken(token).stream()
							.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, authorities);

					authenticationToken.setDetails(userDetails);

					SecurityContextHolder.getContext().setAuthentication(authenticationToken);

				}

			}

		} catch (ExpiredJwtException ex) {

			SecurityExceptionHandler.handleException(response,
					new BaseException(new ErrorMessage(MessageType.TOKEN_IS_EXPİRED, ex.getMessage())), request);
			return;

		} catch (Exception e) {

			SecurityExceptionHandler.handleException(response,
					new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTİON, token)), request);

			return;
		}

		filterChain.doFilter(request, response);

	}

}
