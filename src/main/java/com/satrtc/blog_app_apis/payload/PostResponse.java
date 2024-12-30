package com.satrtc.blog_app_apis.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostResponse {
	
	List<PostDto> allPosts;
	int pagNumber;
	int pageSize;
	long totalElements;
	int totalPages;
	boolean lastPage;

}
