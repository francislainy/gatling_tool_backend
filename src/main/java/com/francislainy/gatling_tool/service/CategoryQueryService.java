package com.francislainy.gatling_tool.service;

import com.francislainy.gatling_tool.dto.CategoryQueryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryQueryService {

    CategoryQueryDto getCategory(UUID id);

    List<CategoryQueryDto> listAllCategories();
}
