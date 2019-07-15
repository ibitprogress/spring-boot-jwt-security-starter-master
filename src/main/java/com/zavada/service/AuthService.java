package com.zavada.service;

import com.zavada.domain.request.SigninRequest;
import com.zavada.domain.request.SignupRequest;

public interface AuthService {

	void signup(SignupRequest request);
	
	String signin(SigninRequest request);
	
}
