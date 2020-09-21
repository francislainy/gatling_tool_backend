package com.francislainy.gatling_tool.service.impl.category;

import com.francislainy.gatling_tool.dto.category.CategoryCreateDto;
import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.dto.category.CategoryUpdateDto;
import com.francislainy.gatling_tool.model.entity.category.Category;
import com.francislainy.gatling_tool.repository.category.CategoryRepository;
import com.francislainy.gatling_tool.service.category.CategoryCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryCommandImpl implements CategoryCommandService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public UUID createCategory(CategoryCreateDto categoryCreateDto) {

        Category newCategory = new Category();
        newCategory.setId(UUID.randomUUID());
        newCategory.setTitle(categoryCreateDto.getTitle());

        return categoryRepository.save(newCategory).getId();
    }


    @Override
    public CategoryQueryDto updateCategory(UUID id, CategoryUpdateDto categoryUpdateDto) {

        if (categoryRepository.findById(id).isPresent()) {

            Category existingCategory = categoryRepository.findById(id).get();

            existingCategory.setTitle(categoryUpdateDto.getTitle());

            Category updatedCategory = categoryRepository.save(existingCategory);

            return new CategoryQueryDto(updatedCategory.getId(), updatedCategory.getTitle());

        } else {
            return null;
        }
    }


    @Override
    public void deleteCategory(UUID id) {

        if (categoryRepository.findById(id).isPresent()) {

            Category existingCategory = categoryRepository.findById(id).get();

            categoryRepository.delete(existingCategory);

        }

    }


}
