package com.francislainy.gatling_tool.service.impl.report;

import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import com.francislainy.gatling_tool.model.entity.category.Category;
import com.francislainy.gatling_tool.model.entity.report.Report;
import com.francislainy.gatling_tool.repository.report.ReportRepository;
import com.francislainy.gatling_tool.service.report.ReportQueryService;
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

            Category category = new Category();
            category.setId(report.getCategory().getId());
            category.setTitle(report.getCategory().getTitle());

            return new ReportQueryDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date(), category, report.getNumberOfUsers(), report.getDuration());

        } else {
            return null;
        }

    }


    @Override
    public ReportQueryDto getReportIncludingCategory(UUID id) {

        if (reportRepository.findReportItemWithCategoryName(id) != null) {
            Report report = reportRepository.findReportItemWithCategoryName(id);

            Category category = new Category();
            category.setId(report.getCategory().getId());
            category.setTitle(report.getCategory().getTitle());

            return new ReportQueryDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date(), category, report.getNumberOfUsers(), report.getDuration());
        } else {
            return null;
        }
    }


    @Override
    public List<ReportQueryDto> listAllReports() {

        List<ReportQueryDto> reportList = new ArrayList<>();

        reportRepository.findAll().forEach(report -> {

            Category category = new Category();
            category.setId(report.getCategory().getId());
            category.setTitle(report.getCategory().getTitle());

            reportList.add(new ReportQueryDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date(), category, report.getNumberOfUsers(), report.getDuration()));

        });

        return reportList;
    }


    @Override
    public List<ReportQueryDto> listAllReportsIncludingCategory() {

        List<ReportQueryDto> reportList = new ArrayList<>();

        reportRepository.findReportListWithCategoryName().forEach(report -> {

            Category category = new Category();
            category.setId(report.getCategory().getId());
            category.setTitle(report.getCategory().getTitle());

            reportList.add(new ReportQueryDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date(), category, report.getNumberOfUsers(), report.getDuration()));

        });

        return reportList;
    }


    public List<ReportQueryDto> listAllReportsByCategory(UUID id) {

        List<ReportQueryDto> reportList = new ArrayList<>();

        reportRepository.findByCategory_Id(id).forEach(report -> {

            Category category = new Category();
            category.setId(report.getCategory().getId());
            category.setTitle(report.getCategory().getTitle());

            reportList.add(new ReportQueryDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date(), category, report.getNumberOfUsers(), report.getDuration()));

        });

        return reportList;
    }

}





