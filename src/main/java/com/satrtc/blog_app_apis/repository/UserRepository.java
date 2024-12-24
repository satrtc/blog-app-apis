package com.satrtc.blog_app_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satrtc.blog_app_apis.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
