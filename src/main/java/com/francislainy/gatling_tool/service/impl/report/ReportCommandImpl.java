package com.francislainy.gatling_tool.service.impl.report;

import com.francislainy.gatling_tool.dto.report.ReportCreateDto;
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

        Category existingCategory = categoryRepository.findById(reportCreateDto.getCategory().getId()).get();
        newReport.setCategory(existingCategory);

        return reportRepository.save(newReport).getId();
    }


    @Override
    public ReportUpdateDto updateReport(UUID id, ReportUpdateDto reportUpdateDto) {

        if (reportRepository.findById(id).isPresent()) {

            Report existingReport = reportRepository.findById(id).get();
            existingReport.setReportTitle(reportUpdateDto.getTitle());
            existingReport.setRun_date(reportUpdateDto.getRunDate());
            existingReport.setCreated_date(reportUpdateDto.getCreatedDate());

            Category existingCategory = categoryRepository.findById(reportUpdateDto.getCategory().getId()).get();
            existingCategory.addReport(existingReport);
            reportRepository.save(existingReport);

            return new ReportUpdateDto(existingReport.getId(),
                    existingReport.getReportTitle(), existingReport.getRun_date(),
                    existingReport.getCreated_date(), existingReport.getCategory());

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
