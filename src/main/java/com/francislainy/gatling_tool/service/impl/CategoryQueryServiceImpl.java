package com.francislainy.gatling_tool.service.impl;

import com.francislainy.gatling_tool.dto.CategoryQueryDto;
import com.francislainy.gatling_tool.model.entity.Category;
import com.francislainy.gatling_tool.repository.CategoryRepository;
import com.francislainy.gatling_tool.service.CategoryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryQueryDto getCategory(UUID id) {

        if (categoryRepository.findById(id).isPresent()) {
            Category category = categoryRepository.findById(id).get();
            return new CategoryQueryDto(category.getId(), category.getCategoryTitle());
        } else {
            return null;
        }

    }


    @Override
    public List<CategoryQueryDto> listAllCategories() {

        List<CategoryQueryDto> categoryList = new ArrayList<>();

        categoryRepository.findAll().forEach(category -> {
            categoryList.add(new CategoryQueryDto(category.getId(), category.getCategoryTitle()));
        });

        return categoryList;

    }

}
