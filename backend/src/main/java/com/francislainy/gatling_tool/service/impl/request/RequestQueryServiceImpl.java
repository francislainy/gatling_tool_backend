package com.francislainy.gatling_tool.service.impl.request;

import com.francislainy.gatling_tool.dto.request.RequestQueryDto;
import com.francislainy.gatling_tool.model.entity.request.Request;
import com.francislainy.gatling_tool.repository.request.RequestRepository;
import com.francislainy.gatling_tool.service.request.RequestQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RequestQueryServiceImpl implements RequestQueryService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public RequestQueryDto getRequest(UUID id) {

        if (requestRepository.findById(id).isPresent()) {
            Request request = requestRepository.findById(id).get();
            return new RequestQueryDto(request.getId(), request.getTitle());
        } else {
            return null;
        }

    }


    @Override
    public List<RequestQueryDto> listAllRequests() {

        List<RequestQueryDto> requestList = new ArrayList<>();

        requestRepository.findAll().forEach(request -> {
            requestList.add(new RequestQueryDto(request.getId(), request.getTitle()));
        });

        return requestList;

    }

}
