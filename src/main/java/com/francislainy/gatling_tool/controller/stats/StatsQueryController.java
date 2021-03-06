package com.francislainy.gatling_tool.controller.stats;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import com.francislainy.gatling_tool.dto.stats.Stats;
import com.francislainy.gatling_tool.service.CsvService;
import com.francislainy.gatling_tool.service.stats.StatsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/api/gatling-tool/stats")
@RestController
public class StatsQueryController {

    @Autowired
    private StatsQueryService statsQueryService;

    @Autowired
    CsvService fileService;

    public StatsQueryController(StatsQueryService statsQueryService) {
        this.statsQueryService = statsQueryService;
    }

    @Operation(summary = "Get the complete list of stats")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<Stats>> listAllStats() {

        Map result = new HashMap();
        result.put("stats", statsQueryService.listAllStats());
        return result;

    }

    @Operation(summary = "Get a stats")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryQueryDto> getStats(@PathVariable(value = "id") UUID id) {
        return new ResponseEntity(statsQueryService.getStats(id), HttpStatus.OK);
    }

    @Operation(summary = "Get the list of stats for a report")
    @GetMapping(value = "/report/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<ReportQueryDto>> getStatsByReport(@PathVariable(value = "id") UUID id) {

        Map result = new HashMap();
        result.put("stats", statsQueryService.listAllStatsByReport(id));
        return result;
    }

    @GetMapping("/csv/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "tutorials.csv";
        InputStreamResource file = new InputStreamResource(fileService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
