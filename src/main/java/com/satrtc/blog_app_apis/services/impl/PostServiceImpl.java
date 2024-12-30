package com.satrtc.blog_app_apis.services.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.satrtc.blog_app_apis.entities.Category;
import com.satrtc.blog_app_apis.entities.Post;
import com.satrtc.blog_app_apis.entities.User;
import com.satrtc.blog_app_apis.exception.ResourceNotFoundException;
import com.satrtc.blog_app_apis.payload.PostDto;
import com.satrtc.blog_app_apis.payload.PostResponse;
import com.satrtc.blog_app_apis.repository.CategoryRepository;
import com.satrtc.blog_app_apis.repository.PostRepository;
import com.satrtc.blog_app_apis.repository.UserRepository;
import com.satrtc.blog_app_apis.services.PostServices;


@Service
public class PostServiceImpl implements PostServices{

	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepositary;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto,int userId,int categoryId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User", "userId", userId));
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->
		                  new ResourceNotFoundException("category", "category_Id", categoryId));
		
		Post postInput=new Post();
		postInput.setAddedDate(new java.util.Date());
		postInput.setImageName("default.png");
		postInput.setCategory(category);
		postInput.setUser(user);
		postInput.setPostTitle(postDto.getTitle());
		
		Post postSaved=this.postRepository.save(postInput);
		return this.modelMapper.map(postSaved, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int id) {
		// TODO Auto-generated method stub
		Post post=this.postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","postID", id));
		
		post.setPostTitle(postDto.getTitle());
		post.setImageName(postDto.getImageName());
		post.setCategory(post.getCategory());
		
		Post updatedPost=this.postRepository.save(post);
		PostDto updatedPostDto=this.modelMapper.map(updatedPost,PostDto.class);
		
		return updatedPostDto;
	}

	@Override
	public void deletePost(int id) {
		// TODO Auto-generated method stub
		Post post=this.postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "postID", id));
		
		this.postRepository.delete(post);
		
	}

	@Override
	public PostDto getPost(int id) {
		// TODO Auto-generated method stub
		Post post=this.postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "postID", id));
		PostDto postDto=this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostbyUser(int userId) {
		// TODO Auto-generated method stub
		User user=this.userRepositary.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId", userId));
		List<Post> allPosts=this.postRepository.findByUser(user);
		
		List<PostDto> postDtos=allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostbyCategory(int categID) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepository.findById(categID).orElseThrow(()-> new ResourceNotFoundException("Category", "categID", categID));
		List<Post> allposts=this.postRepository.findByCategory(category);
		
		List<PostDto> allpostDtos=allposts.stream().map((posts)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		 return allpostDtos;
	}
	
	//method to handle get all posts with pagination

	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize) {
//		List<Post> allPosts=this.postRepository.findAll();
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePosts=this.postRepository.findAll(p);
		List<Post> allPosts=pagePosts.getContent();
		PostResponse postResponse=new PostResponse();
		List<PostDto> allPostsDto=allPosts.stream().map((posts)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		postResponse.setAllPosts(allPostsDto);
		postResponse.setLastPage(pagePosts.isLast());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setPagNumber(pagePosts.getNumber());
		postResponse.setTotalElements(pagePosts.getNumberOfElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		return postResponse;
	}

}
