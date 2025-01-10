package com.satrtc.blog_app_apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satrtc.blog_app_apis.entities.Post;
import com.satrtc.blog_app_apis.payload.ApiConstansts;
import com.satrtc.blog_app_apis.payload.ApiResponse;
import com.satrtc.blog_app_apis.payload.PostDto;
import com.satrtc.blog_app_apis.payload.PostResponse;
import com.satrtc.blog_app_apis.services.PostServices;

import lombok.Builder.Default;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	PostServices postServices;
	
	
	//create post
	@PostMapping("/create-post/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dtoPost,@PathVariable Integer userId, @PathVariable Integer categoryId)
	{
		PostDto postDtoSaved=this.postServices.createPost(dtoPost, userId, categoryId);
		return new ResponseEntity<PostDto>(postDtoSaved,HttpStatus.CREATED);
	}
	
	//update-post
	@PutMapping("/update/{postID}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postID)
	{
		PostDto updatedPostDto=this.postServices.updatePost( postDto, postID);
		return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK);
	}
	
	//get post by id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable int postId)
	{
		PostDto postDto=this.postServices.getPost(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	//get post by user
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPosOfUser(@PathVariable int userId)
	{
		List<PostDto> allpostS=this.postServices.getPostbyUser(userId);
		return new ResponseEntity<List<PostDto>>(allpostS,HttpStatus.OK);
	}
	
	//get post by category
	@GetMapping("/category/{categId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categId){
		List<PostDto> allPosts=this.postServices.getPostbyCategory(categId);
		return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
	}
	
	//getAllPosts 
	@GetMapping("")
	public ResponseEntity<PostResponse> getAllposts(@RequestParam(required = false, defaultValue = ApiConstansts.PAGE_NUMBER) Integer pageNumber,
			@RequestParam(required = false, defaultValue = ApiConstansts.PAGE_SIZE) Integer pageSize,
			@RequestParam(required=false, defaultValue = ApiConstansts.SORT_BY) String sortBy,
			@RequestParam(required = false, defaultValue = ApiConstansts.SORT_DIRECTION)  String sortDirection)
	{
		PostResponse allPostRespone=this.postServices.getAllPosts(pageNumber,pageSize,sortBy,sortDirection);
		return new ResponseEntity<PostResponse>(allPostRespone,HttpStatus.OK);
	}
	
	//delete post by using category
	@DeleteMapping("/delete-post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId)
	{
		this.postServices.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
	}
	
	//search a post by post title
	@GetMapping("/search")
	public ResponseEntity<List<PostDto>> searchPostUsingTitle(@RequestParam("postTitle") String postTitle)
	{
		System.out.println(postTitle);
		List<PostDto> postWithGivenTitle=this.postServices.searchBypostTitle(postTitle);
		return new ResponseEntity<List<PostDto>>(postWithGivenTitle,HttpStatus.OK);
	}
}
