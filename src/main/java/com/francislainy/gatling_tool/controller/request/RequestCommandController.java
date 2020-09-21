package com.francislainy.gatling_tool.controller.request;

import com.francislainy.gatling_tool.dto.request.RequestCreateDto;
import com.francislainy.gatling_tool.dto.request.RequestQueryDto;
import com.francislainy.gatling_tool.dto.request.RequestUpdateDto;
import com.francislainy.gatling_tool.service.request.RequestCommandService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RequestMapping("/api/gatling-tool/request")
@RestController
public class RequestCommandController {

    @Autowired
    private RequestCommandService requestCommandService;

    @Operation(summary = "Create a request")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UUID> createRequest(@RequestBody RequestCreateDto requestCreateDto) {
        return new ResponseEntity<>(requestCommandService.createRequest(requestCreateDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a request")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RequestQueryDto> updateRequest(@PathVariable(value = "id") UUID id,
                                                          @RequestBody RequestUpdateDto requestUpdateDto) {
        return new ResponseEntity<>(requestCommandService.updateRequest(id, requestUpdateDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete a request")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteRequest(@PathVariable(value = "id") UUID id) {

        requestCommandService.deleteRequest(id);
    }

}
