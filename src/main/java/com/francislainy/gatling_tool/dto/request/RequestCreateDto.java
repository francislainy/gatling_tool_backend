package com.francislainy.gatling_tool.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCreateDto {

    private UUID id;
    private String title;

    public RequestCreateDto(String title) {
        this.title = title;
    }

}
