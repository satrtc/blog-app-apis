package com.satrtc.blog_app_apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satrtc.blog_app_apis.entities.Post;
import com.satrtc.blog_app_apis.payload.PostDto;
import com.satrtc.blog_app_apis.services.PostServices;

@RestController
@RequestMapping("/api/user")
public class PostController {
	
	@Autowired
	PostServices postServices;
	
	@PostMapping("/{userId}/category/{categoryId}/create-post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dtoPost,@PathVariable Integer userId, @PathVariable Integer categoryId)
	{
		PostDto postDtoSaved=this.postServices.createPost(dtoPost, userId, categoryId);
		return new ResponseEntity<PostDto>(postDtoSaved,HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}/get-post")
	public ResponseEntity<List<PostDto>> getPosOfUser(@PathVariable int userID)
	{
		List<PostDto> allpostS=this.postServices.getPostbyUser(userID);
		return new ResponseEntity<List<PostDto>>(allpostS,HttpStatus.OK);
	}
}
