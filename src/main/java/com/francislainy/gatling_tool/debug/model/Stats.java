package com.francislainy.gatling_tool.debug.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("stats")
    @Expose
    public Stats_ stats;
    @SerializedName("contents")
    @Expose
    public Contents contents;
    @SerializedName("req_get-last-public-74412")
    @Expose
    public ReqGetLastPublic74412 reqGetLastPublic74412;

}
