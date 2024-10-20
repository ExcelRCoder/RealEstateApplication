package com.example.realestate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.realestate.entity.Post;
import com.example.realestate.exceptions.UserException;
import com.example.realestate.service.PostServiceImpl;

@RestController
@RequestMapping("/api/post")
//@CrossOrigin(origins = "http://localhost:5173" ,allowCredentials="true" , Access-Control-Allow-Credentials:"true")
public class PostController {

	@Autowired
	private PostServiceImpl postService;
	
		
		
		// createPost
		@PostMapping("/addPost") // http://localhost:1234/api/post/addPost
		public ResponseEntity<Post> PostCreation(@Validated @ModelAttribute Post post,@RequestParam("image") MultipartFile file) throws IOException  {
			
			
			postService.addPost(post,file);
			System.out.println("asddsa" + post);

			return new ResponseEntity<Post>(post,HttpStatus.OK);
			
			
			
		}
		
		// getPostDetailsById
		@GetMapping("/getPostById/{id}") // http://localhost:1234/api/post/getPostById/id
		public Post getPostById(@PathVariable("id") Long id) throws UserException {
			Optional<Post> post = postService.getPostById(id);
			if (post.isPresent())
				return post.get();
			else
				throw new UserException("post not found with given id");
		}
		
		@GetMapping("/getAllpost") // http://localhost:1234/api/post/getAllpost
		public List<Post> getAllPost() {
			return postService.getAllPosts();
		}
		
}
