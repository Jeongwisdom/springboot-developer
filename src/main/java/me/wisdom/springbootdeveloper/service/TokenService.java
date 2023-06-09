package me.wisdom.springbootdeveloper.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.wisdom.springbootdeveloper.config.jwt.TokenProvider;
import me.wisdom.springbootdeveloper.domain.User;

@RequiredArgsConstructor
@Service
public class TokenService {

	private final TokenProvider tokenProvider;
	private final RefreshTokenService refreshTokenService;
	private final UserService userService;

	public String createNewAccessToken(String refreshToken) {
		if (!tokenProvider.validToken(refreshToken)) {
			throw new IllegalStateException("Unexpected token");
		}

		Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
		User user = userService.findById(userId);

		return tokenProvider.generateToken(user, Duration.ofHours(2));
	}
}
