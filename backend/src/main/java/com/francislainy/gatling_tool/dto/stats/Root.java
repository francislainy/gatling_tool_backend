package com.francislainy.gatling_tool.dto.stats;

import com.francislainy.gatling_tool.debug.model_manual.Contents;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Root {

    @SerializedName("contents")
    @Expose
    public Contents contents;

}
