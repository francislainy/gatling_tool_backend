package com.francislainy.gatling_tool.repository.category;

import com.francislainy.gatling_tool.model.entity.category.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    List<Category> findByTitle(@Param("categoryTitle") String categoryTitle);
}
