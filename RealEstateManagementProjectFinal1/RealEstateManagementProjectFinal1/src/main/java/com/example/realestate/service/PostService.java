package com.example.realestate.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.realestate.entity.Post;

public interface PostService {
	


	
	Optional<Post> getPostById(Long id);

	Post addPost(Post post, MultipartFile file) throws IOException;
	


	
}
