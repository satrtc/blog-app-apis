package com.satrtc.blog_app_apis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satrtc.blog_app_apis.entities.Comment;
import com.satrtc.blog_app_apis.entities.Post;
import com.satrtc.blog_app_apis.entities.User;
import com.satrtc.blog_app_apis.exception.ResourceNotFoundException;
import com.satrtc.blog_app_apis.payload.CommentDto;
import com.satrtc.blog_app_apis.repository.CommentRepository;
import com.satrtc.blog_app_apis.repository.PostRepository;
import com.satrtc.blog_app_apis.repository.UserRepository;
import com.satrtc.blog_app_apis.services.CommentServices;

@Service
public class CommentServiceImpl implements CommentServices {

	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, int userID, int postID) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userID).orElseThrow(
				()-> new ResourceNotFoundException("user", "userID", userID));
		Post post=this.postRepository.findById(postID).orElseThrow(
				()->new ResourceNotFoundException("post","postID", postID));
		Comment comment=new Comment();
		comment.setCommentDate(new java.util.Date());
		comment.setContent(commentDto.getContent());
		comment.setPost(post);
		comment.setUser(user);
		
		Comment createdComment=this.commentRepository.save(comment);
		
		return modelMapper.map(createdComment, CommentDto.class);
	}

	@Override
	public CommentDto modifyComment(CommentDto commentDto, int commentId) {
		// TODO Auto-generated method stub
		Comment commentFetched=this.commentRepository.findById(commentId).orElseThrow(
				()-> new ResourceNotFoundException("comment", "commentId", commentId));
		commentFetched.setContent(commentDto.getContent());
		Comment updatedComment=this.commentRepository.save(commentFetched);
		return modelMapper.map(updatedComment, CommentDto.class);
	}

	@Override
	public void removeComment(int commentId) {
		
		Comment fetchedComment=this.commentRepository.findById(commentId).orElseThrow(
				()-> new ResourceNotFoundException("comment", "comment", commentId));
		this.commentRepository.delete(fetchedComment);

	}

	@Override
	public List<CommentDto> getAllComments() {
		// TODO Auto-generated method stub
		List<Comment> allComments=this.commentRepository.findAll();
		List<CommentDto> allCommentsDtos=allComments.stream().map(
				                        (comments)->this.modelMapper.map(comments, CommentDto.class)).collect(Collectors.toList());
		return allCommentsDtos;
	}

	@Override
	public List<CommentDto> getCommentsOfUser( int userId) {
		
		User user=this.userRepository.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("user","userId",userId));
		
		List<Comment> allComments=this.commentRepository.findByUser(user);
		List<CommentDto> allCommentsDto=allComments.stream().map(
				                        (comment)->this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		return allCommentsDto;
	}

	@Override
	public List<CommentDto> getCommentsOfPost(int postId) {
		// TODO Auto-generated method stub
		Post fetchedPost=this.postRepository.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("psot", "postId", postId));
		List<Comment> allPostComments=this.commentRepository.findByPost(fetchedPost);
		List<CommentDto> allPostCommentDto=allPostComments.stream().map(
		                                              (comment)->this.modelMapper.
		                                              map(comment, CommentDto.class)).
				                                      collect(Collectors.toList());
		return allPostCommentDto;
	}

	

}
