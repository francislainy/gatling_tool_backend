package com.francislainy.gatling_tool.controller;

import com.francislainy.gatling_tool.dto.CategoryCreateDto;
import com.francislainy.gatling_tool.dto.CategoryQueryDto;
import com.francislainy.gatling_tool.dto.CategoryUpdateDto;
import com.francislainy.gatling_tool.service.CategoryCommandService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RequestMapping("/api/gatling-tool/category")
@RestController
public class CategoryCommandController {

    @Autowired
    private CategoryCommandService categoryCommandService;

    @Operation(summary = "Create a category")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UUID> createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        return new ResponseEntity<>(categoryCommandService.createCategory(categoryCreateDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a category")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryQueryDto> updateCategory(@PathVariable(value = "id") UUID id,
                                                           @RequestBody CategoryUpdateDto categoryUpdateDto) {
        return new ResponseEntity<>(categoryCommandService.updateCategory(id, categoryUpdateDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete a category")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable(value = "id") UUID id) {

        categoryCommandService.deleteCategory(id);
    }

}
