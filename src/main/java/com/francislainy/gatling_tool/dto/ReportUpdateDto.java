package com.francislainy.gatling_tool.dto;

import com.francislainy.gatling_tool.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportUpdateDto {

    private UUID id;
    private String title;
    private String runDate;
    private String createdDate;

    private Category category;

    public ReportUpdateDto(String title) {
        this.title = title;
    }

}
