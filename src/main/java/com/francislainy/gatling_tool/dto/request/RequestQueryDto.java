package com.francislainy.gatling_tool.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestQueryDto {

    private UUID id;
    private String title;

    public RequestQueryDto(String title) {
        this.title = title;
    }

}
