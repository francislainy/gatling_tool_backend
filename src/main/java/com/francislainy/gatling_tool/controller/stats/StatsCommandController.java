package com.francislainy.gatling_tool.controller.stats;

import com.francislainy.gatling_tool.dto.stats.Stats;
import com.francislainy.gatling_tool.service.stats.StatsCommandService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RequestMapping("/api/gatling-tool/stats")
@RestController
public class StatsCommandController {

    @Autowired
    private StatsCommandService statsCommandService;

    @Operation(summary = "Update a stats")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Stats> updateReport(@PathVariable(value = "id") UUID id,
                                              @RequestBody Stats stats) {
        return new ResponseEntity<>(statsCommandService.updateStats(id, stats), HttpStatus.OK);
    }

    @Operation(summary = "Delete a stats")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteReport(@PathVariable(value = "id") UUID id) {

        statsCommandService.deleteStats(id);
    }

}
