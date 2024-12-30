package com.satrtc.blog_app_apis.services;

import java.util.List;

import com.satrtc.blog_app_apis.entities.Post;
import com.satrtc.blog_app_apis.payload.CategoryDto;
import com.satrtc.blog_app_apis.payload.PostDto;
import com.satrtc.blog_app_apis.payload.PostResponse;
import com.satrtc.blog_app_apis.payload.UserDto;

public interface PostServices {
	
	//create post
	public PostDto createPost(PostDto postDto,int userID,int categoryID);
	//update post
	public PostDto updatePost(PostDto postDto, int id);
	//delete post
	public void deletePost(int id);
	//get post a post by its id
	public PostDto getPost(int id);
	//get posts of a user
	public List<PostDto> getPostbyUser(int userId);
	//get posts of a category
	public List<PostDto> getPostbyCategory(int categID);
	//get all posts
	public PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy, String sortDirection);

}
