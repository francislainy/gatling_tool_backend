package com.francislainy.gatling_tool.repository.stats;

import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StatsRepository extends JpaRepository<StatsEntity, UUID> {

    String queryStatsOrderedById = "SELECT * FROM stats WHERE report.id = :reportId ORDER BY id";

    @Query(value = queryStatsOrderedById, nativeQuery = true)
    List<StatsEntity> findByReportId(UUID reportId);

}
