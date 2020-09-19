package com.francislainy.gatling_tool.dto;

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
    private String categoryTitle;
    private UUID categoryId;

    public ReportCreateDto(String reportTitle) {
        this.reportTitle = reportTitle;
    }

}
