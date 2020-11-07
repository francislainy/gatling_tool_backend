package com.francislainy.gatling_tool.dto.report;

import com.francislainy.gatling_tool.model.entity.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportQueryDto {

    private UUID id;
    private String title;
    private Long runDate;
    private Long createdDate;

    private Category category;

    private Integer numberOfUsers;
    private Long duration;

    public ReportQueryDto(UUID id, String title, Long runDate, Long createdDate) {
        this.id = id;
        this.title = title;
        this.runDate = runDate;
        this.createdDate = createdDate;
    }
}
