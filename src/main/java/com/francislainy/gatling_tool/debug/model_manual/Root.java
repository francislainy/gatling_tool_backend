package com.francislainy.gatling_tool.debug.model_manual;

import lombok.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Data
public class Root {

    @SerializedName("contents")
    @Expose
    public Contents contents;

}
