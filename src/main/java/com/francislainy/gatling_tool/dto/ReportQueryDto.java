package com.francislainy.gatling_tool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportQueryDto {

    private UUID id;
    private String reportTitle;
    private String runDate;
    private String createdDate;
    private String categoryTitle;

    public ReportQueryDto(String reportTitle) {
        this.reportTitle = reportTitle;
    }

}
