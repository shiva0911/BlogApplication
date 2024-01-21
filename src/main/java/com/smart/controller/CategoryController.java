package com.smart.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.smart.paylode.ApiResponse;
import com.smart.paylode.CategoryDto;
import com.smart.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category/")
public class CategoryController
{
	@Autowired
	private CategoryService categoryService;
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{categoryId}/")
	public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categortDto,@PathVariable Integer categoryId)
	{
		CategoryDto updatedcat=this.categoryService.updateCategory(categortDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedcat,HttpStatus.OK);
	}
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Integer categoryId)
	{

		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully ! !",true),HttpStatus.OK);
		
	}
	@GetMapping("/{categoryId}/")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer categoryId)
	{
		CategoryDto categoryDto=this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getAllCategories()
	{
		List<CategoryDto>categories=this.categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}
	
	

}
