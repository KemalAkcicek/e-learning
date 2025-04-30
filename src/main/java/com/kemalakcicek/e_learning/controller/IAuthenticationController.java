package com.kemalakcicek.e_learning.controller;

import com.kemalakcicek.e_learning.dto.AuthRequest;
import com.kemalakcicek.e_learning.dto.AuthResponse;
import com.kemalakcicek.e_learning.dto.DtoUserIU;
import com.kemalakcicek.e_learning.dto.DtoUserToken;
import com.kemalakcicek.e_learning.dto.RefreshTokenRequest;

public interface IAuthenticationController {

	public RootEntity<DtoUserToken> register(DtoUserIU request);

	public RootEntity<AuthResponse> authentication(AuthRequest authRequest);

	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
