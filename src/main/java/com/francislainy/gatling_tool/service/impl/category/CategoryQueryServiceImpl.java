package com.francislainy.gatling_tool.service.impl.category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import com.francislainy.gatling_tool.model.entity.category.Category;
import com.francislainy.gatling_tool.repository.category.CategoryRepository;
import com.francislainy.gatling_tool.repository.report.ReportRepository;
import com.francislainy.gatling_tool.service.category.CategoryQueryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ReportRepository reportRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public CategoryQueryDto getCategory(UUID id) throws JsonProcessingException {

        if (categoryRepository.findById(id).isPresent()) {
            Category category = categoryRepository.findById(id).get();

            CategoryQueryDto categoryQueryDto = new CategoryQueryDto(category.getId(), category.getTitle());

            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//            String converter = gson.toJson(categoryQueryDto);
            String converter = mapper.writeValueAsString(categoryQueryDto);

//            categoryQueryDto = gson.fromJson(converter, CategoryQueryDto.class);

            categoryQueryDto = mapper.readValue(converter, CategoryQueryDto.class);


            return categoryQueryDto;



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


