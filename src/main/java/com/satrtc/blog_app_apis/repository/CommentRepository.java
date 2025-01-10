package com.satrtc.blog_app_apis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satrtc.blog_app_apis.entities.Comment;
import com.satrtc.blog_app_apis.entities.Post;
import com.satrtc.blog_app_apis.entities.User;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByPost(Post post);
	List<Comment> findByUser(User user);
}
