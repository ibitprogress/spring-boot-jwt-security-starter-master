package com.zavada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zavada.domain.request.SigninRequest;
import com.zavada.domain.request.SignupRequest;
import com.zavada.domain.response.SigninResponse;
import com.zavada.service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	/*
		{
			"username": "zavada",
			"password": "123",
			"passwordConfirmation": "123"
		}
	*/
	@PostMapping("signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		authService.signup(signupRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/*
		{
			"username": "zavada",
			"password": "123",
		}
	*/
	@PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
    	String token = authService.signin(signinRequest);
        return ResponseEntity.ok(new SigninResponse(token));
    }
}
