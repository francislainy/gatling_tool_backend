package com.francislainy.gatling_tool.service.impl;

import com.francislainy.gatling_tool.dto.CategoryQueryDto;
import com.francislainy.gatling_tool.model.entity.CategoryEntity;
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
            CategoryEntity categoryEntity = categoryRepository.findById(id).get();
            return new CategoryQueryDto(categoryEntity.getId(), categoryEntity.getCategoryTitle());
        } else {
            return null;
        }

    }


    @Override
    public List<CategoryQueryDto> listAllCategories() {

        List<CategoryQueryDto> categoryList = new ArrayList<>();

        categoryRepository.findAll().forEach(categoryEntity -> {
            categoryList.add(new CategoryQueryDto(categoryEntity.getId(), categoryEntity.getCategoryTitle()));
        });

        return categoryList;

    }

}
