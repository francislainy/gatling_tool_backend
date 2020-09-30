package com.francislainy.gatling_tool.dto.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats____ {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("numberOfRequests")
    @Expose
    public NumberOfRequests___ numberOfRequests;
    @SerializedName("minResponseTime")
    @Expose
    public MinResponseTime___ minResponseTime;
    @SerializedName("maxResponseTime")
    @Expose
    public MaxResponseTime___ maxResponseTime;
    @SerializedName("meanResponseTime")
    @Expose
    public MeanResponseTime___ meanResponseTime;
    @SerializedName("standardDeviation")
    @Expose
    public StandardDeviation___ standardDeviation;
    @SerializedName("percentiles1")
    @Expose
    public Percentiles1___ percentiles1;
    @SerializedName("percentiles2")
    @Expose
    public Percentiles2___ percentiles2;
    @SerializedName("percentiles3")
    @Expose
    public Percentiles3___ percentiles3;
    @SerializedName("percentiles4")
    @Expose
    public Percentiles4___ percentiles4;
    @SerializedName("group1")
    @Expose
    public Group1___ group1;
    @SerializedName("group2")
    @Expose
    public Group2___ group2;
    @SerializedName("group3")
    @Expose
    public Group3___ group3;
    @SerializedName("group4")
    @Expose
    public Group4___ group4;
    @SerializedName("meanNumberOfRequestsPerSecond")
    @Expose
    public MeanNumberOfRequestsPerSecond___ meanNumberOfRequestsPerSecond;

}
