package com.kemalakcicek.e_learning.service;

import com.kemalakcicek.e_learning.dto.AuthRequest;
import com.kemalakcicek.e_learning.dto.AuthResponse;
import com.kemalakcicek.e_learning.dto.DtoUserIU;
import com.kemalakcicek.e_learning.dto.DtoUserToken;
import com.kemalakcicek.e_learning.dto.RefreshTokenRequest;

public interface IAuthenticationService {

	public DtoUserToken register(DtoUserIU request);

	public AuthResponse authentication(AuthRequest request);

	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
