package me.wisdom.springbootdeveloper.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.wisdom.springbootdeveloper.domain.User;
import me.wisdom.springbootdeveloper.dto.AddUserRequest;
import me.wisdom.springbootdeveloper.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public Long save(AddUserRequest dto) {
		return userRepository.save(User.builder()
			.email(dto.getEmail())
			.password(bCryptPasswordEncoder.encode(dto.getPassword()))
			.build()).getId();
	}
}
