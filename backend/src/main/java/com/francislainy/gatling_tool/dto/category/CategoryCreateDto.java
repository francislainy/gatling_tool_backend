package com.francislainy.gatling_tool.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryCreateDto {

    private UUID id;
    private String title;

}
