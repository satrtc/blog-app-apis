package com.satrtc.blog_app_apis.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.metamodel.internal.AbstractPojoInstantiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.satrtc.blog_app_apis.payload.ApiResponse;
import com.satrtc.blog_app_apis.payload.UserDto;
import com.satrtc.blog_app_apis.services.UserServices;
import com.satrtc.blog_app_apis.services.impl.UserImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserServices userServices;
	
	//create a user :POST method
	@PostMapping("/create-user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDtoInput)
	{
		UserDto userDto=this.userServices.createUser(userDtoInput);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
	}
	
	//Get all user : Get method
	@GetMapping("/get-all-user")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> allUsers=this.userServices.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);	
	}
	
	//update a user: PUT method
	@PutMapping("/update-user/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userUpdate,@PathVariable int id)
	{
		UserDto updatedUserDto=this.userServices.updateUser(userUpdate, id);
		return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
	}
	
	//delete a user: DELETE method
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<ApiResponse> deleteTheUser(@PathVariable int id)
	{
		this.userServices.deleteUser(id);
		return new ResponseEntity(new ApiResponse("User Deleted",true),HttpStatus.OK);
	}
	
	//get single user: GEt method
	@GetMapping("/get-a-user/{id}")
	public ResponseEntity<UserDto> getSpecificUser(@PathVariable int id)
	{
		UserDto specific_user_dto=this.userServices.getUserById(id);
		return new ResponseEntity<UserDto>(specific_user_dto,HttpStatus.OK);
	}
}
