package com.smart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.config.AppConstants;
import com.smart.dao.RoleRepo;
import com.smart.dao.UserRepo;
import com.smart.entity.Roles;
import com.smart.entity.User;
import com.smart.exceptions.ResourceNotFoundException;
import com.smart.paylode.UserDto;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepo useRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo rolerepo;
    public UserDto createUser(UserDto userDto)
    {
        User user = dtoToUser(userDto);
        User savedUser = useRepo.save(user);
        return userToDto(savedUser);
    }

    public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.useRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = useRepo.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) 
    {
        User user = useRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","ID",userId));
        return userToDto(user);
    }
    @Override
	public List<UserDto> getAllUsers() 
    {
		List<User> users=this.useRepo.findAll();
    	List<UserDto> userDto=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
       return userDto;
     }

	@Override
	public void deleteUser(int userId)
	{
		User user=this.useRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","ID",userId));
    	this.useRepo.delete(user);	
	}

	private User dtoToUser(UserDto userDto) 
    {
    	User user=this.modelMapper.map(userDto, User.class);
    	    return user;
    }

    private UserDto userToDto(User user)
    {
        UserDto userDto =this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // Fetch the NORMAL_USER role or throw an exception if not found
        Roles role = this.rolerepo.findById(AppConstants.NORMAL_USER)
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "id", AppConstants.NORMAL_USER));

        user.getRoles().add(role);

        try {
            User newUser = this.useRepo.save(user);
            return this.modelMapper.map(newUser, UserDto.class);
        } catch (Exception ex) {
            // Handle the exception, log details, and consider throwing a custom exception
            ex.printStackTrace();
            throw new RuntimeException("Error registering new user", ex);
        }
    }

}
