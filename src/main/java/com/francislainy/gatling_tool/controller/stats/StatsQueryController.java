package com.francislainy.gatling_tool.controller.stats;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.dto.stats.Stats;
import com.francislainy.gatling_tool.service.stats.StatsQueryService;
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
@RequestMapping("/api/gatling-tool/stats")
@RestController
public class StatsQueryController {

    @Autowired
    private StatsQueryService statsQueryService;

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

}
