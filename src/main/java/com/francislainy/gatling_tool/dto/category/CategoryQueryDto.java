package com.francislainy.gatling_tool.dto.category;

import com.francislainy.gatling_tool.dto.report.ReportQueryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryQueryDto {

    private UUID id;
    private String title;

    private List<ReportQueryDto> reportQueryDtoList;

    public CategoryQueryDto(String title) {
        this.title = title;
    }

    public CategoryQueryDto(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

}
