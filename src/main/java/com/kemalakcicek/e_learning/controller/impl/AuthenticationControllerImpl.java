package com.kemalakcicek.e_learning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.e_learning.controller.IAuthenticationController;
import com.kemalakcicek.e_learning.controller.RootEntity;
import com.kemalakcicek.e_learning.dto.AuthRequest;
import com.kemalakcicek.e_learning.dto.AuthResponse;
import com.kemalakcicek.e_learning.dto.DtoUserIU;
import com.kemalakcicek.e_learning.dto.DtoUserToken;
import com.kemalakcicek.e_learning.dto.RefreshTokenRequest;
import com.kemalakcicek.e_learning.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class AuthenticationControllerImpl extends RestBaseController implements IAuthenticationController {

	@Autowired
	private IAuthenticationService authenticationService;

	@Override
	@PostMapping(path = "/register")
	public RootEntity<DtoUserToken> register(@Valid @RequestBody DtoUserIU request) {

		return ok(authenticationService.register(request));
	}

	@Override
	@PostMapping(path = "/authenticate")
	public RootEntity<AuthResponse> authentication(@Valid @RequestBody AuthRequest authRequest) {

		return ok(authenticationService.authentication(authRequest));
	}

	@Override
	@PostMapping(path = "/refreshtoken")
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {

		return ok(authenticationService.refreshToken(refreshTokenRequest));
	}

}
