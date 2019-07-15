package com.zavada.service;

import java.util.List;

import com.zavada.domain.UserDTO;

public interface UserService {

	List<UserDTO> findAll();
	
}
