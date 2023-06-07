package me.wisdom.springbootdeveloper.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.wisdom.springbootdeveloper.domain.User;
import me.wisdom.springbootdeveloper.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public User loadUserByUsername(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalArgumentException(email));
	}
}
