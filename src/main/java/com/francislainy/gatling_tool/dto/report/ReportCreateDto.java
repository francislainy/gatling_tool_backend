package com.francislainy.gatling_tool.dto.report;

import com.francislainy.gatling_tool.model.entity.category.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportCreateDto {

    private UUID id;
    private String title;
    private Long runDate;
    private Long createdDate;

    private Category category;

}
