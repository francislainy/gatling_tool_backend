package com.francislainy.gatling_tool.service.impl.category;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import com.francislainy.gatling_tool.model.entity.category.Category;
import com.francislainy.gatling_tool.repository.category.CategoryRepository;
import com.francislainy.gatling_tool.repository.report.ReportRepository;
import com.francislainy.gatling_tool.service.category.CategoryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static util.Utils.createJsonFromClassObject;


@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ReportRepository reportRepository;


    @Override
    public CategoryQueryDto getCategory(UUID id) {

        if (categoryRepository.findById(id).isPresent()) {
            Category category = categoryRepository.findById(id).get();

            return new CategoryQueryDto(category.getId(), category.getTitle());

        } else {
            return null;
        }

    }


    @Override
    public List<CategoryQueryDto> listAllCategories() {

        List<CategoryQueryDto> categoryList = new ArrayList<>();

        categoryRepository.findAll().forEach(category -> {
            categoryList.add(new CategoryQueryDto(category.getId(), category.getTitle()));
        });

        return categoryList;

    }


    @Override
    public CategoryQueryDto listCategoryWithAllReports(UUID id) {

        List<ReportQueryDto> reportList = new ArrayList<>();

        if (categoryRepository.findById(id).isPresent()) {
            Category category = categoryRepository.findById(id).get();

            reportRepository.findByCategory_Id(id).forEach(report -> {
                reportList.add(new ReportQueryDto(report.getId(), report.getReportTitle(), report.getRun_date(), report.getCreated_date()));
            });

            CategoryQueryDto categoryQueryDto = new CategoryQueryDto(category.getId(), category.getTitle(), reportList);

            return categoryQueryDto;

        } else {
            return null;
        }
    }


}


