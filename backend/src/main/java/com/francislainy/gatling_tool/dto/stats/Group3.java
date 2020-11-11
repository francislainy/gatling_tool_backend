package com.francislainy.gatling_tool.dto.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Group3 {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("percentage")
    @Expose
    public Integer percentage;

}
