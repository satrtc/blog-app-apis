package com.satrtc.blog_app_apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satrtc.blog_app_apis.entities.Category;
import com.satrtc.blog_app_apis.exception.ResourceNotFoundException;
import com.satrtc.blog_app_apis.payload.CategoryDto;
import com.satrtc.blog_app_apis.payload.UserDto;
import com.satrtc.blog_app_apis.repository.CategoryRepository;
import com.satrtc.blog_app_apis.services.CategoryServices;


@Service
public class CategoryServiceImp implements CategoryServices{

	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category=this.modelmapper.map(categoryDto, Category.class);
		Category saveCategory=this.categoryRepository.save(category);
		return this.modelmapper.map(saveCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category saveCategory=this.categoryRepository.save(category);
		return this.modelmapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		this.categoryRepository.deleteById(id);
	}

	@Override
	public CategoryDto getCategory(int id) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
		return this.modelmapper.map(category,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		List<Category> categories=this.categoryRepository.findAll();
	    List<CategoryDto> categoriesDto=categories.stream().map(categ->this.modelmapper.map(categ,CategoryDto.class)).collect(Collectors.toList());
	return categoriesDto;
	}

}
