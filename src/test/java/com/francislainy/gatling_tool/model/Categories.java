package com.francislainy.gatling_tool.model;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Categories {

    @SerializedName("categories")
    @Expose
    public List<CategoryQueryDto> categories = null;

}
