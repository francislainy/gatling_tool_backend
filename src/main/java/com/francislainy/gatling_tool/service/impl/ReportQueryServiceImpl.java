package com.francislainy.gatling_tool.service.impl;

import com.francislainy.gatling_tool.dto.ReportQueryDto;
import com.francislainy.gatling_tool.model.entity.Report;
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
            Report report = reportRepository.findById(id).get();
            return new ReportQueryDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date(), report.getCategoryTitle(), report.getCategory().getId());
        } else {
            return null;
        }
    }

//    @Override
//    public List<ReportQueryDto> listAllReports() {
//
//        List<ReportQueryDto> reportList = new ArrayList<>();
//
//        reportRepository.findAll().forEach(report -> {
//            reportList.add(new ReportQueryDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date(), report.getCategoryTitle(), report.getCategory().getId()));
//        });
//
//        return reportList;
//    }


    @Override
    public List<ReportQueryDto> listAllReports() {

        List<ReportQueryDto> reportList = new ArrayList<>();

        reportRepository.findReportWithCategoryName().forEach(report -> {
            reportList.add(new ReportQueryDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date(), report.getCategoryTitle(), report.getCategory().getId()));
        });

        return reportList;
    }
}
