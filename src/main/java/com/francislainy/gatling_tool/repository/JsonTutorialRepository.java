package com.francislainy.gatling_tool.repository;

import com.francislainy.gatling_tool.model.entity.JsonTutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonTutorialRepository extends JpaRepository<JsonTutorial, Long> {
}
