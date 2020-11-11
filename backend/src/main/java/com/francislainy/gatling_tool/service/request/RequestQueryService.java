package com.francislainy.gatling_tool.service.request;

import com.francislainy.gatling_tool.dto.request.RequestQueryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RequestQueryService {

    RequestQueryDto getRequest(UUID id);

    List<RequestQueryDto> listAllRequests();
}
