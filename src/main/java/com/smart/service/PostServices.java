package com.smart.service;



import com.smart.paylode.PostDto;
import com.smart.paylode.PostResponse;

import java.util.*;


public interface PostServices 
{
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize ,String sortBy ,String sortDir);
	PostDto getPostById(int postid);
	List<PostDto>getPostByCategory(Integer categoryId);
	List<PostDto>getPostByUser(Integer userId);
	List<PostDto>searchPosts(String keyword);
	PostDto updatePost(PostDto postDto, int postid);
	void deletePost(int postid);
	
 
}
