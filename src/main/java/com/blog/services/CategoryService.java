package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;
import com.blog.payloads.UserDto;

public interface CategoryService  {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategory();
	void deleteCategory(Integer categoryId);
}
