package com.francislainy.gatling_tool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryQueryDto {

    private UUID id;
    private String categoryTitle;

    public CategoryQueryDto(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

}
