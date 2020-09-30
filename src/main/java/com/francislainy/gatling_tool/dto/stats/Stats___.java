package com.francislainy.gatling_tool.dto.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats___ {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("numberOfRequests")
    @Expose
    public NumberOfRequests__ numberOfRequests;
    @SerializedName("minResponseTime")
    @Expose
    public MinResponseTime__ minResponseTime;
    @SerializedName("maxResponseTime")
    @Expose
    public MaxResponseTime__ maxResponseTime;
    @SerializedName("meanResponseTime")
    @Expose
    public MeanResponseTime__ meanResponseTime;
    @SerializedName("standardDeviation")
    @Expose
    public StandardDeviation__ standardDeviation;
    @SerializedName("percentiles1")
    @Expose
    public Percentiles1__ percentiles1;
    @SerializedName("percentiles2")
    @Expose
    public Percentiles2__ percentiles2;
    @SerializedName("percentiles3")
    @Expose
    public Percentiles3__ percentiles3;
    @SerializedName("percentiles4")
    @Expose
    public Percentiles4__ percentiles4;
    @SerializedName("group1")
    @Expose
    public Group1__ group1;
    @SerializedName("group2")
    @Expose
    public Group2__ group2;
    @SerializedName("group3")
    @Expose
    public Group3__ group3;
    @SerializedName("group4")
    @Expose
    public Group4__ group4;
    @SerializedName("meanNumberOfRequestsPerSecond")
    @Expose
    public MeanNumberOfRequestsPerSecond__ meanNumberOfRequestsPerSecond;

}
