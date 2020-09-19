package com.francislainy.gatling_tool.service.impl;

import com.francislainy.gatling_tool.dto.*;
import com.francislainy.gatling_tool.model.entity.Category;
import com.francislainy.gatling_tool.model.entity.Report;
import com.francislainy.gatling_tool.repository.ReportRepository;
import com.francislainy.gatling_tool.service.ReportCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportCommandImpl implements ReportCommandService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public UUID createReport(ReportCreateDto reportCreateDto) {

        Report newReport = new Report();
        newReport.setId(UUID.randomUUID());

        newReport.setRun_date(reportCreateDto.getRunDate());
        newReport.setCreated_date(reportCreateDto.getCreatedDate());
        newReport.setReportTitle(reportCreateDto.getReportTitle());

        Category category = new Category();
        category.setId(reportCreateDto.getCategory().getId());
        category.setCategoryTitle(reportCreateDto.getCategory().getCategoryTitle());
        newReport.setCategory(category);

        return reportRepository.save(newReport).getId();
    }


    @Override
    public ReportQueryDto updateReport(UUID id, ReportUpdateDto reportUpdateDto) {

        if (reportRepository.findById(id).isPresent()) {

            Report existingReport = reportRepository.findById(id).get();

            existingReport.setReportTitle(reportUpdateDto.getReportTitle());
            existingReport.setRun_date(reportUpdateDto.getRunDate());
            existingReport.setCreated_date(reportUpdateDto.getCreatedDate());

            Category category = new Category();
            category.setId(reportUpdateDto.getCategory().getId());
            category.setCategoryTitle(reportUpdateDto.getCategory().getCategoryTitle());
            existingReport.setCategory(category);

            Report updatedReport = reportRepository.save(existingReport);

            return new ReportQueryDto(updatedReport.getId(), updatedReport.getReportTitle(), updatedReport.getRun_date(), updatedReport.getCreated_date(), category);

        } else {
            return null;
        }

    }


    @Override
    public void deleteReport(UUID id) {

        if (reportRepository.findById(id).isPresent()) {

            Report existingReport = reportRepository.findById(id).get();

            reportRepository.delete(existingReport);

        }
    }

}
