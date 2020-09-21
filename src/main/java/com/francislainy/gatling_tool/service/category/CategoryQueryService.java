package com.francislainy.gatling_tool.service.category;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryQueryService {

    CategoryQueryDto getCategory(UUID id);

    List<CategoryQueryDto> listAllCategories();
}
