package com.francislainy.gatling_tool.service.report;

import com.francislainy.gatling_tool.dto.report.ReportCreateDto;
import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import com.francislainy.gatling_tool.dto.report.ReportUpdateDto;

import java.util.UUID;

public interface ReportCommandService {

    UUID createReport(ReportCreateDto reportCreateDto);

    ReportUpdateDto updateReport(UUID id, ReportUpdateDto reportUpdateDto);

    void deleteReport(UUID id);
}
