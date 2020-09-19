package com.francislainy.gatling_tool.service;

import com.francislainy.gatling_tool.dto.*;

import java.util.UUID;

public interface ReportCommandService {

    UUID createReport(ReportCreateDto reportCreateDto);

    ReportQueryDto updateReport(UUID id, ReportUpdateDto reportUpdateDto);

    void deleteReport(UUID id);
}
