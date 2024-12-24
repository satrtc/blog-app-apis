package com.satrtc.blog_app_apis.services;

import java.util.List;

import com.satrtc.blog_app_apis.entities.User;
import com.satrtc.blog_app_apis.payload.UserDto;

public interface UserServices {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
}
