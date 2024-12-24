package com.satrtc.blog_app_apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.satrtc.blog_app_apis.entities.User;
import com.satrtc.blog_app_apis.exception.ResourceNotFoundException;
import com.satrtc.blog_app_apis.payload.UserDto;
import com.satrtc.blog_app_apis.repository.UserRepository;
import com.satrtc.blog_app_apis.services.UserServices;

public class UserImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.userDtoToUser(userDto);
		User savedUser=userRepository.save(user);
		
		return this.userToDto(savedUser);
	}



	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId)
		                   .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		
		user.setName(user.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepository.save(user);
		UserDto updatedUserDto=this.userToDto(user);
		return updatedUserDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		
		UserDto userDto=this.userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> userList=this.userRepository.findAll();
		List<UserDto> userDtos=userList.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
	return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		this.userRepository.delete(user);
	}
	
	private User userDtoToUser(UserDto userDto)
	{
		User user=new User();
		user.setId(userDto.getId());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDto userToDto(User savedUser) 
	{
		
		UserDto userDto=new UserDto();
		userDto.setId(savedUser.getId());
		userDto.setAbout(savedUser.getAbout());
		userDto.setEmail(savedUser.getEmail());
		userDto.setName(savedUser.getName());
		userDto.setPassword(savedUser.getPassword());
		
		return userDto;
	}
}
