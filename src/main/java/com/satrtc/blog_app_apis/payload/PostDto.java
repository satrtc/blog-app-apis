package com.satrtc.blog_app_apis.payload;

import java.util.Date;

import com.satrtc.blog_app_apis.entities.Category;
import com.satrtc.blog_app_apis.entities.User;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	String title;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	
}
