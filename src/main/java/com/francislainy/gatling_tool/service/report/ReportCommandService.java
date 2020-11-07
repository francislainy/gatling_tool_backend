package com.francislainy.gatling_tool.service.report;

import com.francislainy.gatling_tool.dto.report.ReportCreateDto;
import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import com.francislainy.gatling_tool.dto.report.ReportQueryDtoFileUploaded;
import com.francislainy.gatling_tool.dto.report.ReportUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ReportCommandService {

    ReportCreateDto createReport(ReportCreateDto reportCreateDto);

    ReportUpdateDto updateReport(UUID id, ReportUpdateDto reportUpdateDto);

    void deleteReport(UUID id);

    ReportQueryDtoFileUploaded saveIndexHtmlFile(MultipartFile file, UUID id);
}
