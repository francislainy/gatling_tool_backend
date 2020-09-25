package com.francislainy.gatling_tool.repository;

import com.francislainy.gatling_tool.model.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
