package com.satrtc.blog_app_apis.services;

import java.util.List;

import com.satrtc.blog_app_apis.entities.Comment;
import com.satrtc.blog_app_apis.payload.CommentDto;

public interface CommentServices {

	//createComment
	public CommentDto createComment(CommentDto commentDto, int userID, int postID);
	
	//updateComment
	public CommentDto modifyComment(CommentDto commentDto, int commentId);
	
	//deleteComment
	public void removeComment(int commentId);
	
	//get all comments
	public List<CommentDto> getAllComments(int pageNumbe, int pageSize);
	
	//get all comments by a user
	public List<CommentDto> getCommentsOfUser(int pageNumber, int pageSize, int userId);
	
	//get all comments of a post
	public List<CommentDto> getCommentsOfPost(int pageNumber, int pageSize, int postId);
		
	
}
