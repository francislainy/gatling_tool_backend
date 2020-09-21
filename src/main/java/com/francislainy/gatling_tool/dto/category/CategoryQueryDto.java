package com.francislainy.gatling_tool.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryQueryDto {

    private UUID id;
    private String title;

    public CategoryQueryDto(String title) {
        this.title = title;
    }

}
