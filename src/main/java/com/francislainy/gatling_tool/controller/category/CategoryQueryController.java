package com.francislainy.gatling_tool.controller.category;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import com.francislainy.gatling_tool.service.category.CategoryQueryService;
import com.francislainy.gatling_tool.service.report.ReportQueryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/api/gatling-tool/category")
@RestController
public class CategoryQueryController {

    @Autowired
    private CategoryQueryService categoryQueryService;

    @Autowired
    private ReportQueryService reportQueryService;

    @Operation(summary = "Get the complete list of categories")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<CategoryQueryDto>> listAllCategories() {

        Map result = new HashMap();
        result.put("categories", categoryQueryService.listAllCategories());
        return result;

    }

    @Operation(summary = "Get a category")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryQueryDto> getCategory(@PathVariable(value = "id") UUID id) {
        return new ResponseEntity<>(categoryQueryService.getCategory(id), HttpStatus.OK);
    }

    @Operation(summary = "Get the list of reports for a category")
    @GetMapping(value = "/{id}/include-reports", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, CategoryQueryDto> getReportByCategory(@PathVariable(value = "id") UUID id) {

        Map result = new HashMap();
        result.put("category", categoryQueryService.listCategoryWithAllReports(id));
        return result;
    }

}
