package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.smart.paylode.ApiResponse;
import com.smart.paylode.CommentDto;
import com.smart.service.CommentService;


@RestController
@RequestMapping("/api/")

public class CommentController 
{
	@Autowired
	private CommentService commentService;
	
	@PostMapping("posts/{postid}/comment")
	public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto comment,@PathVariable Integer postid)
	{
		CommentDto createComment=this.commentService.createComment(comment, postid);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.OK);
		
	}
	@DeleteExchange("/comment/{cid}")
	public ResponseEntity<ApiResponse>deleteComment(@PathVariable Integer cid)
	{
		this.commentService.deleteComment(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment delete sucessfully",true),HttpStatus.OK);
	}

}
