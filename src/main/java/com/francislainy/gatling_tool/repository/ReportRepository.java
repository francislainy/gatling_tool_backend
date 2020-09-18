package com.francislainy.gatling_tool.repository;

import com.francislainy.gatling_tool.model.entity.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReportRepository extends CrudRepository<Report, UUID> {

    List<Report> findByReportTitle(@Param("reportTitle") String reportTitle);

    String myQuery = "SELECT report.id, report.report_title, report.created_date, report.run_date, report.category_id, category.category_title\n" +
            "FROM report\n" +
            "         INNER JOIN category on report.category_id = category.id";

    @Query(value = myQuery, nativeQuery = true)
    List<Report> findReportWithCategoryName();

}
