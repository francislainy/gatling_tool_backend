package com.francislainy.gatling_tool.service;

import com.francislainy.gatling_tool.dto.CategoryCreateDto;
import com.francislainy.gatling_tool.dto.CategoryQueryDto;
import com.francislainy.gatling_tool.dto.CategoryUpdateDto;

import java.util.UUID;

public interface CategoryCommandService {

    UUID createCategory(CategoryCreateDto categoryCreateDto);

    CategoryQueryDto updateCategory(UUID id, CategoryUpdateDto categoryUpdateDto);

    void deleteCategory(UUID id);
}
