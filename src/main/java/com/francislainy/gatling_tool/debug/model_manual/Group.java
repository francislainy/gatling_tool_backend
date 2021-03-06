package com.francislainy.gatling_tool.debug.model_manual;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("stats")
    @Expose
    public Stats stats;
    @SerializedName("contents")
    @Expose
    public Contents_ contents;

}
