package com.satrtc.blog_app_apis.payload;

import java.util.Date;

import com.satrtc.blog_app_apis.entities.Post;
import com.satrtc.blog_app_apis.entities.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private int commentId;
	private String content;
	private Date commentDate;
	private UserDto user;
	private PostDto post;
	
}
