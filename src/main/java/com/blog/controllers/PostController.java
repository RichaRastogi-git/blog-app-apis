package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiReponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//Create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId, @PathVariable Integer categoryId){
		PostDto pot = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(pot, HttpStatus.CREATED) ;
		
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId,
			@RequestParam(value ="pageNumber", defaultValue = "0", required = false)Integer pageNumber, 
			@RequestParam (value ="pageSize", defaultValue = "10", required = false)Integer pagesize){
		List<PostDto> postsDto =  this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsDto, HttpStatus.OK);
		
	}
	
	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
	      List<PostDto> postsDto =  this.postService.getPostByCategory(categoryId);
		  return new ResponseEntity<List<PostDto>>(postsDto, HttpStatus.OK);	
	}
		
	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value ="pageNumber", defaultValue = "0", required = false)Integer pageNumber, 
			@RequestParam (value ="pageSize", defaultValue = "10", required = false)Integer pagesize){
		PostResponse posts = this.postService.getAllPost(pageNumber, pagesize);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);	
	}
	
	// get post by Id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto pot = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(pot, HttpStatus.OK);	
	}
	
	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePosts(@RequestBody PostDto postDto, @PathVariable Integer postId){
		PostDto post = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.ACCEPTED);	
		
	}
	
	// deleted post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiReponse> deletePosts(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiReponse>(new ApiReponse("Post Deleted successfully", true), HttpStatus.ACCEPTED);	
		
	}

}
