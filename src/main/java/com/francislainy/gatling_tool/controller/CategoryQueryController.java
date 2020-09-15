package com.francislainy.gatling_tool.controller;

import com.francislainy.gatling_tool.dto.CategoryQueryDto;
import com.francislainy.gatling_tool.service.CategoryQueryService;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<CategoryQueryDto>> listAllCategories() {

        Map result = new HashMap();
        result.put("categories", categoryQueryService.listAllCategories());
        return result;

//        return new ResponseEntity<>(categoryQueryService.listAllCategories(), HttpStatus.OK);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryQueryDto> getCategory(@PathVariable(value = "id") UUID id) {
        return new ResponseEntity<>(categoryQueryService.getCategory(id), HttpStatus.OK);
    }

}
