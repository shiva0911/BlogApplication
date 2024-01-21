package com.smart.paylode;

import java.util.Date;

import com.smart.entity.Category;
import com.smart.entity.Comment;
import com.smart.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
public class PostDto
{
	private Integer postid;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto categoryDto;
	private UserDto user;
	private Set<CommentDto>comments=new HashSet<>();
	

}
