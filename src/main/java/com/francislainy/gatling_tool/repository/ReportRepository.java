package com.francislainy.gatling_tool.repository;

import com.francislainy.gatling_tool.model.entity.ReportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReportRepository extends CrudRepository<ReportEntity, UUID> {

    List<ReportEntity> findByReportTitle(@Param("reportTitle") String reportTitle);
}
