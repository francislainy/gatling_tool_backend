package com.francislainy.gatling_tool.repository;

import com.francislainy.gatling_tool.model.entity.HtmlTutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HtmlTutorialRepository extends JpaRepository<HtmlTutorial, Long> {
}
