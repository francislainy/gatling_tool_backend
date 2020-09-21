package com.francislainy.gatling_tool.controller.request;

import com.francislainy.gatling_tool.dto.request.RequestQueryDto;
import com.francislainy.gatling_tool.service.request.RequestQueryService;
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
@RequestMapping("/api/gatling-tool/request")
@RestController
public class RequestQueryController {

    @Autowired
    private RequestQueryService requestQueryService;

    @Operation(summary = "Get the complete list of requests")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<RequestQueryDto>> listAllRequests() {

        Map result = new HashMap();
        result.put("requests", requestQueryService.listAllRequests());
        return result;

    }

    @Operation(summary = "Get a request")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RequestQueryDto> getRequest(@PathVariable(value = "id") UUID id) {
        return new ResponseEntity<>(requestQueryService.getRequest(id), HttpStatus.OK);
    }

}
