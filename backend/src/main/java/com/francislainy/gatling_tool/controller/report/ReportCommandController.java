package com.francislainy.gatling_tool.controller.report;

import com.francislainy.gatling_tool.dto.report.ReportCreateDto;
import com.francislainy.gatling_tool.dto.report.ReportUpdateDto;
import com.francislainy.gatling_tool.helper.HtmlHelper;
import com.francislainy.gatling_tool.message.ResponseMessage;
import com.francislainy.gatling_tool.service.report.ReportCommandService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@CrossOrigin
@RequestMapping("/api/gatling-tool/report")
@RestController
public class ReportCommandController {

    @Autowired
    private ReportCommandService reportCommandService;

    @Operation(summary = "Create a report")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReportCreateDto> createReport(@RequestBody ReportCreateDto reportCreateDto) {
        return new ResponseEntity<>(reportCommandService.createReport(reportCreateDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a report")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReportUpdateDto> updateReport(@PathVariable(value = "id") UUID id,
                                                        @RequestBody ReportUpdateDto reportUpdateDto) {
        return new ResponseEntity<>(reportCommandService.updateReport(id, reportUpdateDto), HttpStatus.OK);
    }


    @Operation(summary = "Delete a report")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteReport(@PathVariable(value = "id") UUID id) {

        reportCommandService.deleteReport(id);
    }


    @PostMapping("/upload/html/{id}")
    public ResponseEntity uploadIndexFile(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") UUID id) {
        String message = "";

        if (HtmlHelper.hasHtmlFormat(file)) {
            try {

                return new ResponseEntity<>(reportCommandService.saveIndexHtmlFile(file, id), HttpStatus.OK);

            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "! \n" + e.getMessage();
                log.error(message);
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a html file!";
        log.error(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
