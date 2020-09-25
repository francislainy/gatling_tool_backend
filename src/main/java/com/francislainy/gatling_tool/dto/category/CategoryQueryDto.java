package com.francislainy.gatling_tool.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryQueryDto {

    @Expose()
    private UUID id;
    @Expose()
    private String title;

    @JsonIgnore
    private List<ReportQueryDto> reports;

    public CategoryQueryDto(String title) {
        this.title = title;
    }

    public CategoryQueryDto(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

}
