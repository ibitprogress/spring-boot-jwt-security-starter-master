package com.zavada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zavada.domain.UserDTO;
import com.zavada.repository.UserRepository;
import com.zavada.service.UserService;
import com.zavada.utils.ObjectMapperUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapperUtils modelMapper;

	@Override
	public List<UserDTO> findAll() {
		return modelMapper.mapAll(userRepository.findAll(), UserDTO.class);
	}

}
