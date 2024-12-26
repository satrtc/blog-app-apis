package com.satrtc.blog_app_apis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satrtc.blog_app_apis.entities.Category;
import com.satrtc.blog_app_apis.entities.Post;
import com.satrtc.blog_app_apis.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}
