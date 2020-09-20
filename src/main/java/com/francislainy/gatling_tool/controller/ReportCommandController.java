package com.francislainy.gatling_tool.controller;

import com.francislainy.gatling_tool.dto.*;
import com.francislainy.gatling_tool.service.ReportCommandService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RequestMapping("/api/gatling-tool/report")
@RestController
public class ReportCommandController {

    @Autowired
    private ReportCommandService reportCommandService;

    @Operation(summary = "Create a report")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UUID> createReport(@RequestBody ReportCreateDto reportCreateDto) {
        return new ResponseEntity<>(reportCommandService.createReport(reportCreateDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a report")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReportQueryDto> updateReport(@PathVariable(value = "id") UUID id,
                                                       @RequestBody ReportUpdateDto reportUpdateDto) {
        return new ResponseEntity<>(reportCommandService.updateReport(id, reportUpdateDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete a report")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteReport(@PathVariable(value = "id") UUID id) {

        reportCommandService.deleteReport(id);
    }

}
