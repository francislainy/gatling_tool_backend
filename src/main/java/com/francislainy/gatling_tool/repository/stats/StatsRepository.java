package com.francislainy.gatling_tool.repository.stats;

import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface StatsRepository extends CrudRepository<StatsEntity, UUID> {

    List<StatsEntity> findByReportId(UUID reportId);

}
