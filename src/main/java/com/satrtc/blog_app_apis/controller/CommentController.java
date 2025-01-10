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

import com.satrtc.blog_app_apis.entities.Comment;
import com.satrtc.blog_app_apis.exception.ResourceNotFoundException;
import com.satrtc.blog_app_apis.payload.ApiConstansts;
import com.satrtc.blog_app_apis.payload.ApiResponse;
import com.satrtc.blog_app_apis.payload.CommentDto;
import com.satrtc.blog_app_apis.payload.PostDto;
import com.satrtc.blog_app_apis.repository.CommentRepository;
import com.satrtc.blog_app_apis.services.CommentServices;
import com.satrtc.blog_app_apis.services.impl.CommentServiceImpl;

import lombok.Builder.Default;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	CommentServices commentServices;
	@Autowired
	CommentRepository commentRepository;
	//create comment end point
	@PostMapping("/create-comment/user/{userId}/post/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer userId,@PathVariable Integer postId)
	{
		CommentDto commentDto2=this.commentServices.createComment(commentDto, userId, postId);
		return new ResponseEntity<CommentDto>(commentDto2,HttpStatus.CREATED);
	}
	
	//update comment end point
	@PutMapping("/update-comment/{commentId}")
	public ResponseEntity<CommentDto> updateSelectedComment(@RequestBody CommentDto commentDto, @PathVariable Integer commentId)
	{
		CommentDto updatedCommentDto=this.commentServices.modifyComment(commentDto,commentId);
		return new ResponseEntity<CommentDto>(updatedCommentDto,HttpStatus.OK);
	}
	
	//delete comment end point
	@DeleteMapping("delete-comment/{commentId}")
	public ApiResponse deleteSelectedComment(@PathVariable Integer commentId)
	{
		this.commentServices.removeComment(commentId);
		return new ApiResponse("comment deleted successully", true);
	}
	
	//get comment of a post end point
	@GetMapping("/get-post-comment/post/{postId}")
	public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable Integer postId)
	{
		List<CommentDto> allCommentsDto=this.commentServices.getCommentsOfPost(postId);
		return new ResponseEntity<List<CommentDto>>(allCommentsDto,HttpStatus.OK);
	}
	
	//get comments of a user end point
	@GetMapping("/get-user-comments/user/{userId}")
	public ResponseEntity<List<CommentDto>> getUserComments(@PathVariable Integer userId)
	{
		List<CommentDto> userCommentsDto=this.commentServices.getCommentsOfUser(userId);
		return new ResponseEntity<List<CommentDto>>(userCommentsDto,HttpStatus.OK);
	}
	
	//get all comments
	@GetMapping("")
	public ResponseEntity<List<CommentDto>> getAllComments()
	{
		List<CommentDto> allCommentsDto=this.commentServices.getAllComments();
		return new ResponseEntity<List<CommentDto>>(allCommentsDto,HttpStatus.OK);
		
	}
}
