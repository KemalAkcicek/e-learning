package com.kemalakcicek.e_learning.jwt;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	public static final String SECRET_KEY = "a2VtYWxha2NpY2Vrc29mdHdhcmVkZXZlbG9wZXJiYWNrZW5kZGV2ZWxvcGVy";

	public String generetadToken(UserDetails userDetails) {

		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", roles);

		return Jwts.builder().setSubject(userDetails.getUsername())
				.addClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();

	}

	public <T> T exporedToken(String token, Function<Claims, T> claimsFunction) {

		Claims claims = getClaims(token);

		return claimsFunction.apply(claims);
	}

	public List<String> getRolesFromToken(String token) {

		Claims claims = getClaims(token);

		Object object = claims.get("roles");

		if (object instanceof List) {

			return (List<String>) object;
		}

		return Collections.emptyList();

	}

	public String getUserNameByToken(String token) {

		return exporedToken(token, Claims::getSubject);
	}

	public boolean isTokenValid(String token) {

		Date expiredDate = exporedToken(token, Claims::getExpiration);

		return new Date().before(expiredDate);
	}

	public Claims getClaims(String token) {

		Claims claims = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();

		return claims;
	}

	public Key getKey() {

		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

		return Keys.hmacShaKeyFor(keyBytes);

	}

}
