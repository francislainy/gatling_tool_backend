package com.francislainy.gatling_tool.service.request;

import com.francislainy.gatling_tool.dto.request.RequestCreateDto;
import com.francislainy.gatling_tool.dto.request.RequestQueryDto;
import com.francislainy.gatling_tool.dto.request.RequestUpdateDto;

import java.util.UUID;

public interface RequestCommandService {

    UUID createRequest(RequestCreateDto categoryCreateDto);

    RequestQueryDto updateRequest(UUID id, RequestUpdateDto requestUpdateDto);

    void deleteRequest(UUID id);
}
