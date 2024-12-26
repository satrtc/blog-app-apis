package com.satrtc.blog_app_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satrtc.blog_app_apis.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
