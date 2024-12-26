package com.satrtc.blog_app_apis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.satrtc.blog_app_apis.repository.UserRepository;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	UserRepository repository;
	@Test
	void contextLoads() {
		String classname=repository.getClass().getName();
		String packagename=repository.getClass().getPackageName();  	
		System.out.println(classname);
		System.out.println(packagename);
	}

}
