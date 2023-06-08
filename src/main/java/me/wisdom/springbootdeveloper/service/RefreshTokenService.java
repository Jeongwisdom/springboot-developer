package me.wisdom.springbootdeveloper.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.wisdom.springbootdeveloper.domain.RefreshToken;
import me.wisdom.springbootdeveloper.repository.RefreshTokenRepository;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;

	public RefreshToken findByRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken)
			.orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
	}
}
