package com.smart.service;

import com.smart.paylode.CommentDto;

public interface CommentService
{
	CommentDto createComment(CommentDto commentDto,Integer postid);
	void deleteComment(Integer cid);


}
