package com.satrtc.blog_app_apis.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.satrtc.blog_app_apis.payload.ApiResponse;
import com.satrtc.blog_app_apis.payload.CategoryDto;
import com.satrtc.blog_app_apis.payload.UserDto;
import com.satrtc.blog_app_apis.services.CategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServices categoryServices;
	
	//create categories
	@PostMapping("create-category")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto savedCategoryDto=this.categoryServices.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(savedCategoryDto, HttpStatus.CREATED);
	}

	//update categories
	@PutMapping("/update-category/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable int id)
	{
		CategoryDto updatedCategoryDto=this.categoryServices.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updatedCategoryDto,HttpStatus.OK);
	}
	
	//delete categories
	@DeleteMapping("delete-category/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id)
	{
		this.categoryServices.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted",true),HttpStatus.OK);
	}
	
	//get category
	@GetMapping("get-category/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable int id)
	{
		CategoryDto ctgDto=this.categoryServices.getCategory(id);
		return new ResponseEntity<CategoryDto>(ctgDto,HttpStatus.OK);
	}
	
	//get all category
	@GetMapping("get-all-category")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> allcategories=this.categoryServices.getCategories();
		return new ResponseEntity<List<CategoryDto>>(allcategories,HttpStatus.OK);	
	}
}
