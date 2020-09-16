package com.francislainy.gatling_tool.service.impl;

import com.francislainy.gatling_tool.dto.ReportQueryDto;
import com.francislainy.gatling_tool.model.entity.ReportEntity;
import com.francislainy.gatling_tool.repository.ReportRepository;
import com.francislainy.gatling_tool.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public ReportQueryDto getReport(UUID id) {

        if (reportRepository.findById(id).isPresent()) {
            ReportEntity reportEntity = reportRepository.findById(id).get();
            return new ReportQueryDto(reportEntity.getId(), reportEntity.getReportTitle(), reportEntity.getRun_date(), reportEntity.getCreated_date(), reportEntity.getCategoryTitle());
        } else {
            return null;
        }
    }

    @Override
    public List<ReportQueryDto> listAllReports() {

        List<ReportQueryDto> reportList = new ArrayList<>();

        reportRepository.findAll().forEach(reportEntity -> {
            reportList.add(new ReportQueryDto(reportEntity.getId(), reportEntity.getReportTitle(), reportEntity.getRun_date(), reportEntity.getCreated_date(), reportEntity.getCategoryTitle()));
        });

        return reportList;
    }
}
