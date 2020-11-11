package com.francislainy.gatling_tool.debug.model_manual;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Group2 {

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
