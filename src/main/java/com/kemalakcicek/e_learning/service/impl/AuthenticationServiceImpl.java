package com.kemalakcicek.e_learning.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kemalakcicek.e_learning.dto.AuthRequest;
import com.kemalakcicek.e_learning.dto.AuthResponse;
import com.kemalakcicek.e_learning.dto.DtoUserIU;
import com.kemalakcicek.e_learning.dto.DtoUserToken;
import com.kemalakcicek.e_learning.dto.RefreshTokenRequest;
import com.kemalakcicek.e_learning.exception.BaseException;
import com.kemalakcicek.e_learning.exception.ErrorMessage;
import com.kemalakcicek.e_learning.exception.MessageType;
import com.kemalakcicek.e_learning.jwt.JWTService;
import com.kemalakcicek.e_learning.model.RefreshToken;
import com.kemalakcicek.e_learning.model.User;
import com.kemalakcicek.e_learning.repository.RefreshTokenRepository;
import com.kemalakcicek.e_learning.repository.UserRepository;
import com.kemalakcicek.e_learning.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	private User createUser(DtoUserIU authRequest) {

		User user = new User();

		user.setCreateDate(new Date());
		user.setUsername(authRequest.getUsername());
		user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
		user.setFirstName(authRequest.getFirstName());
		user.setLastName(authRequest.getLastName());
		user.setRoleType(authRequest.getRoleType());
		user.setUpdateDate(new Date());

		return user;

	}

	public RefreshToken createRefreshToken(User user) {

		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setCreateDate(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		refreshToken.setUser(user);
		refreshToken.setRefreshToken(UUID.randomUUID().toString());

		return refreshToken;

	}

	public boolean isRefreshTokenExpired(Date expiredDate) {

		return new Date().before(expiredDate);
	}

	@Override
	public DtoUserToken register(DtoUserIU request) {

		User savedUser = userRepository.save(createUser(request));

		DtoUserToken dtoUserToken = new DtoUserToken();

		BeanUtils.copyProperties(savedUser, dtoUserToken, "id");

		return dtoUserToken;
	}

	@Override
	public AuthResponse authentication(AuthRequest request) {

		try {

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					request.getUsername(), request.getPassword());

			authenticationProvider.authenticate(authenticationToken);

			Optional<User> optional = userRepository.findByUsername(request.getUsername());

			String accessToken = jwtService.generetadToken(optional.get());

			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optional.get()));

			return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

		} catch (Exception e) {

			System.out.println("HATANIN NEDENİ:" + e.getMessage());
			e.printStackTrace();

			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_INVALID, request.toString()));

		}

	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

		Optional<RefreshToken> optional = refreshTokenRepository
				.findByRefreshToken(refreshTokenRequest.getRefreshToken());

		if (optional.isEmpty()) {

			throw new BaseException(
					new ErrorMessage(MessageType.NO_REFRESH_TOKEN_INVALID, refreshTokenRequest.getRefreshToken()));
		}
		if (!(isRefreshTokenExpired(optional.get().getExpiredDate()))) {

			throw new BaseException(
					new ErrorMessage(MessageType.REFRESH_TOKEN_EXPIRED, refreshTokenRequest.getRefreshToken()));

		}

		String accessToken = jwtService.generetadToken(optional.get().getUser());

		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optional.get().getUser()));

		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

}
