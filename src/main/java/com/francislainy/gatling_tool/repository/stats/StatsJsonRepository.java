package com.francislainy.gatling_tool.repository.stats;

import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatsJsonRepository extends JpaRepository<StatsEntity, UUID> {
}
