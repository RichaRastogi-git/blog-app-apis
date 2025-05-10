package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {
	
	//Create
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	//Update
	PostDto updatePost(PostDto postDto, Integer postId);
	//Delete
	void deletePost(Integer postId);
	//get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pagesize);
	//get single post
	PostDto getPostById(Integer postId);
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	//search posts
	List<PostDto> searchPosts(String keyword);
	
}
