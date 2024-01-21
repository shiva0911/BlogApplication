package com.smart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smart.dao.CategoryRepo;
import com.smart.entity.Category;

import com.smart.exceptions.ResourceNotFoundException;
import com.smart.paylode.CategoryDto;

@Service
public  class CategoryServiceImpl implements CategoryService
{
	@Autowired
	@Lazy
	private CategoryService categoryService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) 
	{
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addCat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
	
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDes(categoryDto.getCategoryDes());
		Category updatedcat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedcat,CategoryDto.class );
	}

	public void deleteCategory(Integer categoryId) 
	{
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		this.categoryRepo.delete(cat);
        

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	
	}

	public List<CategoryDto> getAllCategories() {
	List<Category> categories=this.categoryRepo.findAll();
	List<CategoryDto> catDtos=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
	return catDtos;
	}

}
