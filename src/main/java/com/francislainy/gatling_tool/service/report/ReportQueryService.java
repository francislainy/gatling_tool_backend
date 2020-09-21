package com.francislainy.gatling_tool.service.report;

import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ReportQueryService {

    ReportQueryDto getReport(UUID id);

    ReportQueryDto getReportIncludingCategory(UUID id);

    List<ReportQueryDto> listAllReports();

    List<ReportQueryDto> listAllReportsIncludingCategory();

    List<ReportQueryDto> listAllReportsByCategory(UUID id);

}
