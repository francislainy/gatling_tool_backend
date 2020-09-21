package com.francislainy.gatling_tool.service.category;

import com.francislainy.gatling_tool.dto.category.CategoryCreateDto;
import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.dto.category.CategoryUpdateDto;

import java.util.UUID;

public interface CategoryCommandService {

    UUID createCategory(CategoryCreateDto categoryCreateDto);

    CategoryQueryDto updateCategory(UUID id, CategoryUpdateDto categoryUpdateDto);

    void deleteCategory(UUID id);
}
