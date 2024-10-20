package com.example.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.realestate.dto.RequestDto;
import com.example.realestate.entity.Post;
import com.example.realestate.repository.PostRepository;
import com.example.realestate.service.FilterSpecification;

@RestController
@RequestMapping("/api/filterSearch")
public class FilterController {
	
	@Autowired
	private FilterSpecification<Post> PostFilterSpecification;
	
	@Autowired
	private PostRepository postRepository;
	
	@PostMapping("/specification")
	public List<Post> getPosts(@RequestBody RequestDto requestDto){
		Specification<Post> searchSpecification=PostFilterSpecification.getSearchSpecification(requestDto.searchControlDto);
		
		return postRepository.findAll(searchSpecification);
	}
}
