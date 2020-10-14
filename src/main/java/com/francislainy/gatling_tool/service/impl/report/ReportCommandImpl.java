package com.francislainy.gatling_tool.service.impl.report;

import com.francislainy.gatling_tool.dto.report.ReportCreateDto;
import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import com.francislainy.gatling_tool.dto.report.ReportUpdateDto;
import com.francislainy.gatling_tool.model.entity.category.Category;
import com.francislainy.gatling_tool.model.entity.report.Report;
import com.francislainy.gatling_tool.repository.category.CategoryRepository;
import com.francislainy.gatling_tool.repository.report.ReportRepository;
import com.francislainy.gatling_tool.service.report.ReportCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportCommandImpl implements ReportCommandService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public UUID createReport(ReportCreateDto reportCreateDto) {

        Report newReport = new Report();
        newReport.setId(UUID.randomUUID());

        newReport.setRun_date(reportCreateDto.getRunDate());
        newReport.setCreated_date(reportCreateDto.getCreatedDate());
        newReport.setReportTitle(reportCreateDto.getTitle());

        Category category = new Category();
        category.setId(reportCreateDto.getCategory().getId());
        category.setTitle(reportCreateDto.getCategory().getTitle());
        newReport.setCategory(category);

        return reportRepository.save(newReport).getId();
    }


    @Override
    public ReportUpdateDto updateReport(UUID id, ReportUpdateDto reportUpdateDto) {

        if (reportRepository.findById(id).isPresent()) {

            Report existingReport = reportRepository.findById(id).get();

            existingReport.setReportTitle(reportUpdateDto.getTitle());
            existingReport.setRun_date(reportUpdateDto.getRunDate());
            existingReport.setCreated_date(reportUpdateDto.getCreatedDate());

            Category category = new Category();
            category.setId(reportUpdateDto.getCategory().getId());
            Category existingCategory = categoryRepository.findById(reportUpdateDto.getCategory().getId()).get();
            category.setTitle(existingCategory.getTitle());
            existingReport.setCategory(category);

            Report updatedReport = reportRepository.save(existingReport);

            return new ReportUpdateDto(updatedReport.getId(), updatedReport.getReportTitle(), updatedReport.getRun_date(), updatedReport.getCreated_date(), category);

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
