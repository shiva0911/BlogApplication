package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
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

import com.smart.paylode.ApiResponse;
import com.smart.paylode.PostDto;
import com.smart.paylode.PostResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.smart.service.FileService;
import com.smart.service.PostServices;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController 
{
	@Autowired
	private PostServices postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	@PostMapping("/users/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
		
	}
	@GetMapping("/users/{userId}/posts")
	
	public ResponseEntity< List<PostDto>>getPostByUser(@PathVariable Integer userId)
	{
		List<PostDto>posts=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/category/{categoryId}/posts")
	
	public ResponseEntity< List<PostDto>>getPostByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto>posts=this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/posts")
	public ResponseEntity<PostResponse>getAllPost(
			@RequestParam(value="pageNumber",defaultValue="1", required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="1",required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue="postid",required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc",required=false)String sortDir
			)
	{
		
		PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy ,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	@GetMapping("/posts/{postid}")
	public ResponseEntity<PostDto> getAllPost(@PathVariable Integer postid)
	{

		PostDto postDto=this.postService.getPostById(postid);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("posts/{postid}")
	public ApiResponse deletePost(@PathVariable Integer postid)

	{
		this.postService.deletePost(postid);
		return new ApiResponse("post is successufully deleted",true);
		
	}
	
	@PutMapping("posts/{postid}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postid)

	{
		PostDto updatePost=this.postService.updatePost(postDto,postid);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	@GetMapping("posts/search/{keywords}")
	public ResponseEntity<List<PostDto>>searchPostByTitle(@PathVariable("keywords")String keywords)
	{
		List<PostDto> result=this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
		
		
	}
	@PostMapping("posts/image/uplode/{postid}")
	public ResponseEntity<PostDto>uplodeImage(
			@RequestParam("image")MultipartFile image,
			@PathVariable Integer postid
			
			) throws IOException
	{
		PostDto psotDto=this.postService.getPostById(postid);

		String fileName= this.fileService.uplodeImage(path, image);
		psotDto.setImageName(fileName);
		PostDto updatePost=this.postService.updatePost(psotDto, postid);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	@GetMapping(value="posts/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downlodeImage(@PathVariable("imageName") String imageName,HttpServletResponse response)throws IOException
	{
				InputStream resource=this.fileService.getResource(path, imageName);
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				StreamUtils.copy(resource,response.getOutputStream());
	}
}
