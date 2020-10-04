package com.francislainy.gatling_tool.dto.stats;

import com.francislainy.gatling_tool.debug.model_manual.Group1;
import com.francislainy.gatling_tool.debug.model_manual.Group2;
import com.francislainy.gatling_tool.debug.model_manual.Group3;
import com.francislainy.gatling_tool.debug.model_manual.Group4;
import com.francislainy.gatling_tool.debug.model_manual.MaxResponseTime;
import com.francislainy.gatling_tool.debug.model_manual.MeanNumberOfRequestsPerSecond;
import com.francislainy.gatling_tool.debug.model_manual.MeanResponseTime;
import com.francislainy.gatling_tool.debug.model_manual.MinResponseTime;
import com.francislainy.gatling_tool.debug.model_manual.NumberOfRequests;
import com.francislainy.gatling_tool.debug.model_manual.Percentiles1;
import com.francislainy.gatling_tool.debug.model_manual.Percentiles2;
import com.francislainy.gatling_tool.debug.model_manual.Percentiles3;
import com.francislainy.gatling_tool.debug.model_manual.Percentiles4;
import com.francislainy.gatling_tool.debug.model_manual.StandardDeviation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Stats {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("numberOfRequests")
    @Expose
    public NumberOfRequests numberOfRequests;
    @SerializedName("minResponseTime")
    @Expose
    public MinResponseTime minResponseTime;
    @SerializedName("maxResponseTime")
    @Expose
    public MaxResponseTime maxResponseTime;
    @SerializedName("meanResponseTime")
    @Expose
    public MeanResponseTime meanResponseTime;
    @SerializedName("standardDeviation")
    @Expose
    public StandardDeviation standardDeviation;
    @SerializedName("percentiles1")
    @Expose
    public Percentiles1 percentiles1;
    @SerializedName("percentiles2")
    @Expose
    public Percentiles2 percentiles2;
    @SerializedName("percentiles3")
    @Expose
    public Percentiles3 percentiles3;
    @SerializedName("percentiles4")
    @Expose
    public Percentiles4 percentiles4;
    @SerializedName("group1")
    @Expose
    public Group1 group1;
    @SerializedName("group2")
    @Expose
    public Group2 group2;
    @SerializedName("group3")
    @Expose
    public Group3 group3;
    @SerializedName("group4")
    @Expose
    public Group4 group4;
    @SerializedName("meanNumberOfRequestsPerSecond")
    @Expose
    public MeanNumberOfRequestsPerSecond meanNumberOfRequestsPerSecond;

    public Stats(String name) {
    }
}


