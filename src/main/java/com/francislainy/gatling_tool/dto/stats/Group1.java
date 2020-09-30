package com.francislainy.gatling_tool.dto.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group1 {

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



