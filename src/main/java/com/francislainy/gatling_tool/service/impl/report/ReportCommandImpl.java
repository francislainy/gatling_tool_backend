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
    public ReportCreateDto createReport(ReportCreateDto reportCreateDto) {

        Report report = new Report();

        report.setRun_date(reportCreateDto.getRunDate());
        report.setCreated_date(reportCreateDto.getCreatedDate());
        report.setReportTitle(reportCreateDto.getTitle());

        Category existingCategory = categoryRepository.findById(reportCreateDto.getCategory().getId()).get();
        report.setCategory(existingCategory);

        Category category = new Category(existingCategory.getId(), existingCategory.getTitle());

        return new ReportCreateDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date(), category);
        
    }


    @Override
    public ReportUpdateDto updateReport(UUID id, ReportUpdateDto reportUpdateDto) {


        if (reportRepository.findById(id).isPresent()) {

            Report existingReport = reportRepository.findById(id).get();
            existingReport.setReportTitle(reportUpdateDto.getTitle());
            existingReport.setRun_date(reportUpdateDto.getRunDate());
            existingReport.setCreated_date(reportUpdateDto.getCreatedDate());

            Category existingCategory = categoryRepository.findById(reportUpdateDto.getCategory().getId()).get();

            Category category = new Category(existingCategory.getId(), existingCategory.getTitle());
            existingCategory.addReport(existingReport);

            reportRepository.save(existingReport);

            return new ReportUpdateDto(existingReport.getId(),
                    existingReport.getReportTitle(), existingReport.getRun_date(),
                    existingReport.getCreated_date(), category);

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
