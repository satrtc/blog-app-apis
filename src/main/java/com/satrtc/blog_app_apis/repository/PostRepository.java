package com.satrtc.blog_app_apis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.satrtc.blog_app_apis.entities.Category;
import com.satrtc.blog_app_apis.entities.Post;
import com.satrtc.blog_app_apis.entities.User;import com.satrtc.blog_app_apis.payload.PostDto;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);		
	List<Post> findByCategory(Category category);

	@Query("Select p from Post p where p.postTitle LIKE CONCAT('%',:postTitle,'%')")
	List<Post> searchByPostTitle(@Param(value = "postTitle") String Title);
}
