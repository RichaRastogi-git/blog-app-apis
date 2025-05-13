package com.blog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstant;
import com.blog.payloads.ApiReponse;
import com.blog.payloads.ImageResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//Create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId, @PathVariable Integer categoryId){
		PostDto pot = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(pot, HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
			@RequestParam(value ="pageNumber", defaultValue = "0", required = false)Integer pageNumber, 
			@RequestParam (value ="pageSize", defaultValue = "10", required = false)Integer pagesize){
		PostResponse postsDto =  this.postService.getPostByUser(userId,pageNumber, pagesize);
		return new ResponseEntity<PostResponse>(postsDto, HttpStatus.OK);
	}
	
	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
	      List<PostDto> postsDto =  this.postService.getPostByCategory(categoryId);
		  return new ResponseEntity<List<PostDto>>(postsDto, HttpStatus.OK);	
	}
		
	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value ="pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false)Integer pageNumber, 
			@RequestParam (value ="pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false)Integer pagesize,
			@RequestParam(value="sortBy", defaultValue = AppConstant.SORT_BY, required = false)String sortBy,
			@RequestParam(value="sortDir", defaultValue = AppConstant.SORT_DIR, required = false)String sortDir){
		PostResponse posts = this.postService.getAllPost(pageNumber, pagesize, sortBy, sortDir);
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
	
	//search 
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
		List<PostDto> postDto = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);	
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId,
			@RequestParam("image")MultipartFile image) throws IOException{
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost= this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.CREATED);
	}
	

}
