package com.francislainy.gatling_tool.service.impl.request;

import com.francislainy.gatling_tool.dto.request.RequestCreateDto;
import com.francislainy.gatling_tool.dto.request.RequestQueryDto;
import com.francislainy.gatling_tool.dto.request.RequestUpdateDto;
import com.francislainy.gatling_tool.model.entity.request.Request;
import com.francislainy.gatling_tool.repository.request.RequestRepository;
import com.francislainy.gatling_tool.service.request.RequestCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RequestCommandImpl implements RequestCommandService {

    @Autowired
    private RequestRepository requestRepository;


    @Override
    public UUID createRequest(RequestCreateDto requestCreateDto) {

        Request newRequest = new Request();
        newRequest.setId(UUID.randomUUID());
        newRequest.setTitle(requestCreateDto.getTitle());

        return requestRepository.save(newRequest).getId();
    }


    @Override
    public RequestQueryDto updateRequest(UUID id, RequestUpdateDto requestUpdateDto) {

        if (requestRepository.findById(id).isPresent()) {

            Request existingRequest = requestRepository.findById(id).get();

            existingRequest.setTitle(requestUpdateDto.getTitle());

            Request updatedRequest = requestRepository.save(existingRequest);

            return new RequestQueryDto(updatedRequest.getId(), updatedRequest.getTitle());

        } else {
            return null;
        }
    }


    @Override
    public void deleteRequest(UUID id) {

        if (requestRepository.findById(id).isPresent()) {

            Request existingRequest = requestRepository.findById(id).get();

            requestRepository.delete(existingRequest);

        }

    }


}
