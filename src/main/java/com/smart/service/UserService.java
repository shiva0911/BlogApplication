package com.smart.service;

import com.smart.paylode.UserDto;
import java.util.*;
public interface UserService
 {
public UserDto createUser(UserDto userdto);
	
	public UserDto registerNewUser(UserDto user);
	public UserDto updateUser(UserDto userdto , Integer userId);
	
	public UserDto getUserById(Integer userId);
	
	public List<UserDto> getAllUsers();
	
	public void deleteUser(int userId);

}
