package com.zavada.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	private String username;
	
	@JsonIgnore
	private String password;
}
