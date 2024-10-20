package com.example.realestate.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.realestate.entity.Post;
import com.example.realestate.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileUploadService fileUploadService;


	@Override
	public Post addPost(Post post,MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			String fileName;
			try {
				fileName = fileUploadService.saveFile(file);
				post.setImageUrl(fileName);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
        return postRepository.save(post);
		
	}
	
	 // Get all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get post by ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // Update post
    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id)
                      .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(postDetails.getTitle());
        post.setDescription(postDetails.getDescription());
        post.setPrice(postDetails.getPrice());
        post.setAddress(postDetails.getAddress());
      
        post.setUser(postDetails.getUser());

        return postRepository.save(post);
    }

    // Delete post
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

   

    // Find posts by user ID
    public List<Post> findPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

	
	

	

	
	
	
	
  
}
