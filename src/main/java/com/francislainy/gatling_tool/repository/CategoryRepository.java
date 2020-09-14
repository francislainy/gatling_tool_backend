package com.francislainy.gatling_tool.repository;

import com.francislainy.gatling_tool.model.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<CategoryEntity, UUID> {

    List<CategoryEntity> findByCategoryTitle(@Param("categoryTitle") String categoryTitle);
}
