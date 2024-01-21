package com.smart.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.CommentRepo;
import com.smart.dao.PostRepo;
import com.smart.entity.Comment;
import com.smart.entity.Post;
import com.smart.exceptions.ResourceNotFoundException;
import com.smart.paylode.CommentDto;

@Service
public class CommentServiceImpl implements CommentService
{
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postid)
	{
		Post post=this.postRepo.findById((long)postid).orElseThrow(()->new ResourceNotFoundException("Post","POST ID",postid));
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment saveComment=this.commentRepo.save(comment);
		return this.modelMapper.map(saveComment,CommentDto.class);
}

	@Override
	public void deleteComment(Integer cid)
	{
		Comment com=this.commentRepo.findById(cid).orElseThrow(()->new ResourceNotFoundException("comment","comment id",cid));
		this.commentRepo.delete(com);
		
		
	}


}
