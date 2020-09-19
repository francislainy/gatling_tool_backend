package com.francislainy.gatling_tool.dto;

import com.francislainy.gatling_tool.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportCreateDto {

    private UUID id;
    private String reportTitle;
    private String runDate;
    private String createdDate;

    private Category category;

    public ReportCreateDto(String reportTitle) {
        this.reportTitle = reportTitle;
    }

}
