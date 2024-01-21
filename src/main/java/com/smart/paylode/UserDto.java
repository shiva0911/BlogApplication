package com.smart.paylode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

import com.smart.entity.Roles;

@Getter
@Setter
@NoArgsConstructor
public class UserDto 
{ 
	@NotNull
	private int id;
	@NotEmpty
	@Size(min=4,message="user name must be 4 char !!")
	private String name;
	@Email(message="Email address not valida !!")
	private String email;
	@NotEmpty
	@Size(min=3,max=10,message="Password must be minimum of 3 char max of 10 char")
	private String password;
	@NotEmpty
	private String about;
	private Set<Roles>roles=new HashSet<>();

}
