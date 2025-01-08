package com.satrtc.blog_app_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satrtc.blog_app_apis.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
