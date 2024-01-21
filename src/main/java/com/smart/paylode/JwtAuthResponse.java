package com.smart.paylode;

import lombok.Data;

@Data
public class JwtAuthResponse 
{
	private UserDto user;
	
	private String token;

	

}
