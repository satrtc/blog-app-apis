package com.satrtc.blog_app_apis.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.satrtc.blog_app_apis.payload.CategoryDto;

public interface CategoryServices {
	
	//create post
	public CategoryDto createCategory(CategoryDto categoryDto);
	//update post
	public CategoryDto updateCategory(CategoryDto categoryDto, int id);
	//delete post
	public void deleteCategory(int id);
	//get-post
	public CategoryDto getCategory(int id);
	//get-all-post
	public List<CategoryDto> getCategories();
	
}
