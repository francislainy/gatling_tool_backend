package com.francislainy.gatling_tool.repository;

import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HtmlRepository extends JpaRepository<StatsEntity, Long> {
}
