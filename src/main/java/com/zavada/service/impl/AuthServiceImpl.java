package com.zavada.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zavada.config.jwt.JwtTokenProvider;
import com.zavada.domain.request.SigninRequest;
import com.zavada.domain.request.SignupRequest;
import com.zavada.entity.RoleEntity;
import com.zavada.entity.UserEntity;
import com.zavada.exception.AlreadyExistsException;
import com.zavada.exception.ServerException;
import com.zavada.repository.UserRepository;
import com.zavada.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void signup(SignupRequest request) {

		if (userRepository.existsByUsername(request.getUsername())) {
			throw new AlreadyExistsException("User with username [" + request.getUsername() + "] already exists");
		}
		
		if(request.getPassword().equals(request.getPasswordConfirmation())) {
			String password = request.getPassword();
			
			UserEntity user = new UserEntity();
			user.setUsername(request.getUsername());
			user.setPassword(passwordEncoder.encode(password));
			
			Set<RoleEntity> roles = new HashSet<>();
			roles.add(new RoleEntity("USER"));
			user.setRoles(roles);
			
			userRepository.save(user);
			
		} else {
			throw new ServerException("Passwords not match");
		}
	}

	@Override
	public String signin(SigninRequest request) {
		final Authentication authentication = authenticationManager
				.authenticate(
						new UsernamePasswordAuthenticationToken(
								request.getUsername(), 
								request.getPassword()
							)
						);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		return token;
	}

}
