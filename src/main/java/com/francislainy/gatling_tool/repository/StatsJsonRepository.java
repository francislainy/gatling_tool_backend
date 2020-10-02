package com.francislainy.gatling_tool.repository;

import com.francislainy.gatling_tool.model.entity.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatsJsonRepository extends JpaRepository<StatsEntity, UUID> {
}
