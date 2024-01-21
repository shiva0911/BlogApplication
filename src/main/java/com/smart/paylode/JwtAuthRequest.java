package com.smart.paylode;

import lombok.Data;

@Data
public class JwtAuthRequest 
{
	private String userName;
	private String password;

}
